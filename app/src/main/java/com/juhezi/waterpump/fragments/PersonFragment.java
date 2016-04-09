package com.juhezi.waterpump.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.juhezi.waterpump.R;

/**
 * PersonFragment
 *
 * @author: 乔云瑞
 * @time: 2016/4/7 21:52
 * <p/>
 * 个人中心
 */
public class PersonFragment extends BaseFragment {

    private static final String TAG = "PersonFragment";

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_person, container, false);
        SpannableString spannableString = new SpannableString(" HELLO WORLD");
        spannableString.setSpan(new ImageSpan(getActivity(), R.mipmap.ic_launcher), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((TextView) (rootView.findViewById(R.id.show))).setText(spannableString);
        return rootView;
    }

}
