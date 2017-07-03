package com.sstgroup.xabaapp.ui.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.models.Program;
import com.sstgroup.xabaapp.ui.adapters.DialogItemChooserAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

public class CustomChooserDialog extends Dialog {
    private Activity activity;
    private boolean isSingleChoice;
    private OnCustomChooserDialogClosed onCustomChooserDialogClosed;
    private List<String> items;
    private List<String> selectedItems;

    @BindView(R.id.rv_chooser)
    RecyclerView rvChooser;
    @BindView(R.id.btn_close)
    Button btnClose;

    public CustomChooserDialog(Activity activity,
                               List<String> items,
                               List<String> selectedItems,
                               boolean isSingleChoice,
                               OnCustomChooserDialogClosed onCustomChooserDialogClosed) {
        super(activity);
        this.activity = activity;
        this.isSingleChoice = isSingleChoice;
        this.onCustomChooserDialogClosed = onCustomChooserDialogClosed;
        this.items = items;
        this.selectedItems = selectedItems;

    }

    public CustomChooserDialog(Activity activity,
                               List<String> items,
                               boolean isSingleChoice,
                               OnCustomChooserDialogClosed onCustomChooserDialogClosed) {
        super(activity);
        this.activity = activity;
        this.isSingleChoice = isSingleChoice;
        this.onCustomChooserDialogClosed = onCustomChooserDialogClosed;
        this.items = items;
        this.selectedItems = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_chooser);
        ButterKnife.bind(this);
        if (isSingleChoice) {
            btnClose.setVisibility(View.GONE);
        }
        rvChooser.setHasFixedSize(true);
        rvChooser.setLayoutManager(new LinearLayoutManager(activity));
        DialogItemChooserAdapter dialogItemChooserAdapter = new DialogItemChooserAdapter(activity, items, selectedItems);
        dialogItemChooserAdapter.getPositionClicks().subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (selectedItems.contains(s)) {
                    selectedItems.remove(s);
                } else {
                    selectedItems.add(s);
                }
                if (isSingleChoice) {
                    closeDialog();
                }
            }
        });
        rvChooser.setAdapter(dialogItemChooserAdapter);
    }

    private void closeDialog() {
        onCustomChooserDialogClosed.onCustomChooserDialogClosed(selectedItems);
        dismiss();
    }

    @OnClick(R.id.btn_close)
    public void onBtnCloseClicked() {
        closeDialog();
    }

    public interface OnCustomChooserDialogClosed {
        void onCustomChooserDialogClosed(List<String> selectedItems);
    }

    public static String getSelectedPrograms(List<String> selectedPrograms) {
        StringBuilder selectedProgramsString = new StringBuilder();
        for (String programName : selectedPrograms) {
            if (selectedPrograms.indexOf(programName) > 0) {
                selectedProgramsString.append(", ");
            }
            selectedProgramsString.append(programName);
        }
        return selectedProgramsString.toString();
    }

    public static String getSelectedProgramsFromObjects(List<Program> selectedPrograms) {
        ArrayList<String> programNames = new ArrayList<>();
        for (Program program : selectedPrograms) {
            if (program.getName() != null) {
                programNames.add(program.getName());
            }
        }
        return getSelectedPrograms(programNames);
    }
}