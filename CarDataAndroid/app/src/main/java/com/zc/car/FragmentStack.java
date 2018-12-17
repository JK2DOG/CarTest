package com.zc.car;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;


public class FragmentStack {
    private FragmentManager manager;
    private int id;

    public FragmentStack(FragmentManager manager, int id) {
        this.manager = manager;
        this.id = id;
    }

    public void push(Class<? extends BaseFragment> clz, boolean transit) {
        FragmentTransaction transaction = manager.beginTransaction();
        if (transit) {
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        }
        try {
            BaseFragment fragment = clz.newInstance();
            List<Fragment> fragmentList = manager.getFragments();
            int size = fragmentList.size();
            if (size > 0) {
                for (int i = size - 1; i >= 0; i--) {
                    Fragment tmp = fragmentList.get(i);
                    if (tmp instanceof BaseFragment) {
                        tmp.setUserVisibleHint(false);
                        transaction.hide(tmp);
                        break;
                    }
                }
            }
            transaction.add(id, fragment).addToBackStack(null);
            transaction.commitAllowingStateLoss();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public void pushWithTitle(Class<? extends BaseFragment> clz, String newTitle, String oldTitle, boolean transit) {
        FragmentTransaction transaction = manager.beginTransaction();
        if (transit) {
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        }
        try {
            BaseFragment fragment = clz.newInstance();
            List<Fragment> fragmentList = manager.getFragments();
            int size = fragmentList.size();
            if (size > 0) {
                for (int i = size - 1; i >= 0; i--) {
                    Fragment tmp = fragmentList.get(i);
                    if (tmp instanceof BaseFragment) {
                        tmp.setUserVisibleHint(false);
                        transaction.hide(tmp);
                        break;
                    }
                }
            }
            Bundle bundle = new Bundle();
            bundle.putString("currentTitle", newTitle);
            bundle.putString("prevTitle", oldTitle);
            fragment.setArguments(bundle);
            transaction.add(id, fragment).addToBackStack(null);
            transaction.commitAllowingStateLoss();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public void pushWithExtraAndTitle(Class<? extends BaseFragment> clz, Bundle extra,
                                      String newTitle, String oldTitle, boolean transit) {
        FragmentTransaction transaction = manager.beginTransaction();
        if (transit) {
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        }
        try {
            BaseFragment fragment = clz.newInstance();
            List<Fragment> fragmentList = manager.getFragments();
            int size = fragmentList.size();
            if (size > 0) {
                for (int i = size - 1; i >= 0; i--) {
                    Fragment tmp = fragmentList.get(i);
                    if (tmp instanceof BaseFragment) {
                        transaction.hide(tmp);
                        break;
                    }
                }
            }
            extra.putString("currentTitle", newTitle);
            extra.putString("prevTitle", oldTitle);
            fragment.setArguments(extra);
            transaction.add(id, fragment).addToBackStack(null);
            transaction.commitAllowingStateLoss();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    public void pop() {
        List<Fragment> fragments = manager.getFragments();
        if (fragments.size() > 0) {
            manager.popBackStack();
            if (fragments.size() > 0) {
                fragments.get(fragments.size() - 1).setUserVisibleHint(true);
            }
        }
    }

    public int stackCount() {
        return manager.getBackStackEntryCount();
    }

    public Fragment getTopPage() {
        List<Fragment> fragments = manager.getFragments();
        if (fragments != null && fragments.size() > 0) {
            return fragments.get(fragments.size() - 1);
        }
        return null;
    }

    public <T extends Fragment> T getFragment(Class<T> clz) {
        List<Fragment> fragments = manager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment.getClass() == clz) {
                return (T) fragment;
            }
        }
        return null;
    }


}
