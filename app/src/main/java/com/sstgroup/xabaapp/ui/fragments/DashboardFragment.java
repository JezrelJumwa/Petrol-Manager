package com.sstgroup.xabaapp.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.models.Currency;
import com.sstgroup.xabaapp.models.User;
import com.sstgroup.xabaapp.ui.activities.HomeActivity;
import com.sstgroup.xabaapp.ui.widgets.CirclesProgress;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by julianlubenov on 6/8/17.
 */

public class DashboardFragment extends BaseFragment {
    @BindView(R.id.txt_referral_id)
    TextView txtReferralId;
    @BindView(R.id.txt_total_registered_workers)
    TextView txtTotalRegisteredWorkers;
    @BindView(R.id.txt_balance)
    TextView txtBalance;
    @BindView(R.id.txt_balance_currency)
    TextView txtBalanceCurrency;
    @BindView(R.id.txt_registered_workers)
    TextView txtWorkersToGo;
    @BindView(R.id.round_circle_progresses)
    CirclesProgress circlesProgress;

    public static DashboardFragment newInstance() {

        Bundle args = new Bundle();

        DashboardFragment fragment = new DashboardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected void initFields() {

    }

    @Override
    protected void initViews(View rootView) {
        User user = xabaDbHelper.getLoggedUser(activity);
        txtReferralId.setText(String.valueOf(user.getId()));
        txtTotalRegisteredWorkers.setText(String.valueOf(user.getTotalReferrals()));

        Currency currency = xabaDbHelper.getCurrency(user.getCurrencyId());

        //!!!!!Math abs because server sends negative values some times
        txtBalance.setText(String.valueOf(Math.abs(user.getCurrentBalance())));
        txtBalanceCurrency.setText(currency.getCode());

        txtWorkersToGo.setText(String.valueOf((user.getPayoutThreshold() - (user.getTotalReferrals() * user.getPerWorker())) / user.getPerWorker()));

        circlesProgress.setmFirstMax(user.getPayoutThreshold());
        circlesProgress.setmFirstMin(0);
        //!!!!!Math abs because server sends negative values some times
        circlesProgress.setmValueFirst(Math.abs(user.getCurrentBalance()));

        circlesProgress.setmSecondMax(user.getPayoutThreshold());
        circlesProgress.setmSecondMin(0);
        circlesProgress.setmValueSecond((user.getTotalReferrals() * user.getPerWorker()));
    }

    @OnClick(R.id.btn_register_another_worker)
    public void onClick() {
        if (activity instanceof HomeActivity) {
            ((HomeActivity) activity).openAddWorker();
        }
    }

}
