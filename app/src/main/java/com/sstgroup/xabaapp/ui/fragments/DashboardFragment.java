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

public class  DashboardFragment extends BaseFragment {
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
        txtReferralId.setText(" " + user.getId());
        txtTotalRegisteredWorkers.setText(" " + user.getTotalReferrals());

        Currency currency = xabaDbHelper.getCurrency(user.getCurrencyId());

        txtBalance.setText(String.valueOf(Math.max(user.getCurrentBalance(), 0)));
        txtBalanceCurrency.setText(currency.getCode());
//        int workersToGo = (user.getCurrentBalance() - (user.getTotalReferrals() * user.getPerWorker())) / user.getPerWorker();
        int workersToGo = (int) Math.max(Math.ceil((user.getPayoutThreshold() - (user.getPerWorker() * user.getTotalReferrals()))/ user.getPerWorker()), 0);
        txtWorkersToGo.setText(String.valueOf(workersToGo));

        //payoutThreshold is balance where user will get payed in this case 95
        circlesProgress.setmFirstMax(user.getPayoutThreshold());
        circlesProgress.setmFirstMin(0);
        circlesProgress.setmValueFirst(
                Math.max(Math.min(user.getCurrentBalance(), user.getPayoutThreshold()),
                        0));

        circlesProgress.setmSecondMax(user.getPayoutThreshold());
        circlesProgress.setmSecondMin(0);
        circlesProgress.setmValueSecond(
                Math.max(Math.min(user.getTotalReferrals() * user.getPerWorker(), user.getPayoutThreshold()),
                        0));
    }

    @OnClick(R.id.btn_register_another_worker)
    public void onClick() {
        if (activity instanceof HomeActivity) {
            ((HomeActivity) activity).openAddWorker();
        }
    }

}
