package com.jptravel.tech.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.jp_travel.android.R;
import com.jptravel.tech.internal.EmptyViewMethodAccessor;
import com.jptravel.tech.internal.LoadMoreLayout;
import com.jptravel.tech.internal.PullToRefreshLayout;

public class PullToRefreshListView extends PullToRefreshAdapterViewBase<ListView> implements OnScrollListener {

	private PullToRefreshLayout headerLoadingView;
	private PullToRefreshLayout footerLoadingView;
	private LoadMoreLayout loadMore;
	private ImageView nohit;
	private int total = -1;
	private FrameLayout frame;

	class InternalListView extends ListView implements EmptyViewMethodAccessor {

		public InternalListView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		@Override
		public void setEmptyView(View emptyView) {
			PullToRefreshListView.this.setEmptyView(emptyView);
		}

		@Override
		public void setEmptyViewInternal(View emptyView) {
			super.setEmptyView(emptyView);
		}

		@Override
		public ContextMenuInfo getContextMenuInfo() {
			return super.getContextMenuInfo();
		}

	}

	@Override
	public final void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		if (totalItemCount > 9 && (firstVisibleItem + visibleItemCount == totalItemCount)) {
			// only process first event
			if (totalItemCount != total) {
				total = totalItemCount;
				setVisible(true);
			}
		}
		super.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
	}

	public void resetTotal() {
		total = -1;
	}

	public PullToRefreshListView(Context context) {
		super(context);

		this.setDisableScrollingWhileRefreshing(false);
	}

	public PullToRefreshListView(Context context, int mode) {
		super(context, mode);
		this.setDisableScrollingWhileRefreshing(false);
	}

	public PullToRefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setDisableScrollingWhileRefreshing(false);
	}

	@Override
	public ContextMenuInfo getContextMenuInfo() {
		return ((InternalListView) getRefreshableView()).getContextMenuInfo();
	}

	public void setTime(String time) {
		super.setTimeLastRefresh(time);

		if (null != headerLoadingView) {
			headerLoadingView.setTime(time);
		}
	}

	public void setNoHit() {
		boolean added = false;
		for (int i = 0; i < frame.getChildCount(); i++) {
			if (frame.getChildAt(i).equals(nohit)) {
				added = true;
				break;
			}
		}
		if (!added) {
			frame.addView(nohit);
		}
	}

	public void setNoTitleVM() {
		setNoHit();
	}

	public void removeNoHit() {
		for (int i = 0; i < frame.getChildCount(); i++) {
			if (frame.getChildAt(i).equals(nohit)) {
				frame.removeView(nohit);
			}
		}
		frame.setBackgroundResource(R.drawable.bg_loadmore);
	}

	public final void onRefreshComplete() {
		loadMore.setVisibility(View.INVISIBLE);
		onEndRefresh();
	}

	@Override
	public void setReleaseLabel(String releaseLabel) {
		super.setReleaseLabel(releaseLabel);

		if (null != headerLoadingView) {
			headerLoadingView.setReleaseLabel(releaseLabel);
		}
		if (null != footerLoadingView) {
			footerLoadingView.setReleaseLabel(releaseLabel);
		}
	}

	@Override
	public void setPullLabel(String pullLabel) {
		super.setPullLabel(pullLabel);

		if (null != headerLoadingView) {
			headerLoadingView.setPullLabel(pullLabel);
		}
		if (null != footerLoadingView) {
			footerLoadingView.setPullLabel(pullLabel);
		}
	}

	public void setVisible(boolean isVisible) {
		if (isVisible) {
			loadMore.setVisibility(View.VISIBLE);
		} else {
			loadMore.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public void setRefreshingLabel(String refreshingLabel) {
		super.setRefreshingLabel(refreshingLabel);

		if (null != headerLoadingView) {
			headerLoadingView.setRefreshingLabel(refreshingLabel);
		}
		if (null != footerLoadingView) {
			footerLoadingView.setRefreshingLabel(refreshingLabel);
		}
	}

	@Override
	protected final ListView createRefreshableView(Context context, AttributeSet attrs) {
		ListView lv = new InternalListView(context, attrs);

		final int mode = this.getMode();
		// Loading View Strings
		String pullLabel = context.getString(R.string.pull_to_refresh_pull_label);
		String refreshingLabel = context.getString(R.string.pull_to_refresh_refreshing_label);
		String releaseLabel = context.getString(R.string.pull_to_refresh_release_label);
		String pullLoadMore = context.getString(R.string.pull_to_load_label);
		String releaseLoad = context.getString(R.string.release_to_load_label);
		// Add Loading Views
		if (mode == MODE_PULL_DOWN_TO_REFRESH || mode == MODE_BOTH) {
			FrameLayout frame = new FrameLayout(context);
			headerLoadingView = new PullToRefreshLayout(context, MODE_PULL_DOWN_TO_REFRESH, releaseLabel, pullLabel,
					refreshingLabel);
			frame.addView(headerLoadingView, android.view.ViewGroup.LayoutParams.FILL_PARENT,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			headerLoadingView.setVisibility(View.GONE);
			lv.addHeaderView(frame);
		}
		if (mode == MODE_PULL_UP_TO_REFRESH || mode == MODE_BOTH) {
			FrameLayout frame = new FrameLayout(context);
			footerLoadingView = new PullToRefreshLayout(context, MODE_PULL_UP_TO_REFRESH, releaseLoad, pullLoadMore,
					refreshingLabel);
			frame.addView(footerLoadingView, android.view.ViewGroup.LayoutParams.FILL_PARENT,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			footerLoadingView.setVisibility(View.GONE);
			lv.addFooterView(frame);
		}
		frame = new FrameLayout(context);
		loadMore = new LoadMoreLayout(context);
		frame.setBackgroundResource(R.drawable.bg_loadmore);
		frame.addView(loadMore, android.view.ViewGroup.LayoutParams.FILL_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);

		nohit = new ImageView(context);
		nohit.setImageResource(R.drawable.common_no_hit);
		nohit.setLayoutParams(new FrameLayout.LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

		setVisible(false);
		lv.addFooterView(frame);
		// Set it to this so it can be used in ListActivity/ListFragment
		lv.setId(android.R.id.list);
		return lv;
	}

	@Override
	public void setRefreshingInternal(boolean doScroll) {
		super.setRefreshingInternal(false);
		resetTotal();
		setLastSavedFirstVisibleItem(-1);
		final PullToRefreshLayout originalLoadingLayout, listViewLoadingLayout;
		final int selection, scrollToY;

		switch (getCurrentMode()) {
		case MODE_PULL_UP_TO_REFRESH:
			originalLoadingLayout = this.getFooterLayout();
			listViewLoadingLayout = this.footerLoadingView;
			selection = refreshableView.getCount() - 1;
			scrollToY = getScrollY() - getHeaderHeight();
			break;
		case MODE_PULL_DOWN_TO_REFRESH:
		default:
			originalLoadingLayout = this.getHeaderLayout();
			listViewLoadingLayout = this.headerLoadingView;
			selection = 0;
			scrollToY = getScrollY() + getHeaderHeight();
			break;
		}

		if (doScroll) {
			// We scroll slightly so that the ListView's header/footer is at the
			// same Y position as our normal header/footer
			this.setHeaderScroll(scrollToY);
		}

		// Hide our original Loading View
		originalLoadingLayout.setVisibility(View.INVISIBLE);

		// Show the ListView Loading View and set it to refresh
		listViewLoadingLayout.setVisibility(View.VISIBLE);
		listViewLoadingLayout.refreshing();
		if (doScroll) {
			// Make sure the ListView is scrolled to show the loading
			// header/footer
			refreshableView.setSelection(selection);
			// Smooth scroll as normal
			smoothScrollTo(0);
		}
	}
	public void setRefreshingInternalx(boolean doScroll) {
		//super.setRefreshingInternal(false);

		//final PullToRefreshLayout originalLoadingLayout, listViewLoadingLayout;
		final int selection;

		switch (getCurrentMode()) {
		case MODE_PULL_UP_TO_REFRESH:
			selection = refreshableView.getCount() - 1;
			break;
		case MODE_PULL_DOWN_TO_REFRESH:
		default:
			selection = 0;
			break;
		}

		if (doScroll) {
			// We scroll slightly so that the ListView's header/footer is at the
			// same Y position as our normal header/footer
			this.setHeaderScroll(0);
		}

		// Hide our original Loading View
		//originalLoadingLayout.setVisibility(View.INVISIBLE);

		// Show the ListView Loading View and set it to refresh
		if (doScroll) {
			// Make sure the ListView is scrolled to show the loading
			// header/footer
			refreshableView.setSelection(selection);
			// Smooth scroll as normal
			smoothScrollTo(0);
		}
	}
	@Override
	protected void resetHeader() {

		PullToRefreshLayout originalLoadingLayout;
		PullToRefreshLayout listViewLoadingLayout;

		int scrollToHeight = getHeaderHeight();
		final boolean doScroll;

		switch (getCurrentMode()) {
		case MODE_PULL_UP_TO_REFRESH:
			originalLoadingLayout = this.getFooterLayout();
			listViewLoadingLayout = footerLoadingView;
			doScroll = this.isReadyForLoadMore();
			break;
		case MODE_PULL_DOWN_TO_REFRESH:
		default:
			originalLoadingLayout = this.getHeaderLayout();
			listViewLoadingLayout = headerLoadingView;
			scrollToHeight *= -1;
			doScroll = this.isReadyForPullDown();
			break;
		}
		// Set our Original View to Visible
		originalLoadingLayout.setVisibility(View.VISIBLE);

		// Scroll so our View is at the same Y as the ListView header/footer,
		// but only scroll if the ListView is at the top/bottom
		if (doScroll) {
			this.setHeaderScroll(scrollToHeight);
		}

		// Hide the ListView Header/Footer
		listViewLoadingLayout.setVisibility(View.GONE);

		super.resetHeader();
	}

}