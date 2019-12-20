package com.feng.freader.view.fragment.discovery;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.feng.freader.R;
import com.feng.freader.adapter.HotRankAdapter;
import com.feng.freader.base.BaseTabFragment;
import com.feng.freader.constract.IMaleContract;
import com.feng.freader.entity.data.DiscoveryNovelData;
import com.feng.freader.entity.data.HotRankData;
import com.feng.freader.presenter.MalePresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * 发现页面的男生页
 *
 * @author Feng Zhaohao
 * Created on 2019/11/4
 */
public class MaleFragment extends BaseTabFragment<MalePresenter> implements IMaleContract.View {
    private static final String TAG = "MaleFragment";

    private RecyclerView mHotRankRv;

    private HotRankData mHotRankData;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_male;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mHotRankRv = getActivity().findViewById(R.id.rv_male_hot_rank_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mHotRankRv.setLayoutManager(manager);
    }

    @Override
    protected void doInOnCreate() {
        mPresenter.getHotRankData();
        mPresenter.getCategoryNovels();
    }

    @Override
    protected MalePresenter getPresenter() {
        return new MalePresenter();
    }

    @Override
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected int getPosition() {
        return 0;
    }

    /**
     * 获取热门榜单数据成功
     */
    @Override
    public void getHotRankDataSuccess(HotRankData hotRankData) {
        Log.d(TAG, "getHotRankDataSuccess: run");
        mHotRankData = hotRankData;
        initHotRankAdapter();
    }

    /**
     * 获取热门榜单数据失败
     */
    @Override
    public void getHotRankDataError(List<String> errorMsgList) {

    }

    /**
     * 获取分类小说数据成功
     */
    @Override
    public void getCategoryNovelsSuccess(List<DiscoveryNovelData> dataList) {
        Log.d(TAG, "getCategoryNovelsSuccess: " + dataList);
    }

    /**
     * 获取分类小说数据失败
     */
    @Override
    public void getCategoryNovelsError(String errorMsg) {
        Log.d(TAG, "getCategoryNovelsError: " + errorMsg);
    }

    private void initHotRankAdapter() {
        if (mHotRankData == null) {
            return;
        }

        List<HotRankData.NovelInfo> novelInfoList = mHotRankData.getNovelInfoList();
        List<List<String>> hotRankNovelList = new ArrayList<>();
        for (HotRankData.NovelInfo novelInfo : novelInfoList) {
            hotRankNovelList.add(novelInfo.getNameList());
        }
        HotRankAdapter adapter = new HotRankAdapter(getActivity(),
               mHotRankData.getRankNameList(), hotRankNovelList);
        mHotRankRv.setAdapter(adapter);
    }
}
