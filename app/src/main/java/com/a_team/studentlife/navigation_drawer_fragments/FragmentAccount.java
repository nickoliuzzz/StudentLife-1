package com.a_team.studentlife.navigation_drawer_fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a_team.studentlife.OwnEventsActivity;
import com.a_team.studentlife.R;
import com.a_team.studentlife.Server.Retrofit.ApiUtils;
import com.a_team.studentlife.UserInformation.User;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentAccount.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAccount#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAccount extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private TextView userProfileName;
    private TextView showNewsTextView;
    private TextView showShopTextView;
    private TextView showOwnEventsTextView;
    private ImageView userProfilePhoto;

    public FragmentAccount() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAccount.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAccount newInstance(String param1, String param2) {
        FragmentAccount fragment = new FragmentAccount();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_account, container, false);
        showOwnEventsTextView = (TextView) view.findViewById(R.id.own_events_button);
        showOwnEventsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), OwnEventsActivity.class));
            }
        });
        showShopTextView = (TextView) view.findViewById(R.id.shop_account_button);
        showShopTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentTransaction frTransaction =
                        getActivity().getSupportFragmentManager().beginTransaction();
                getActivity().setTitle("Магазин");
                frTransaction.replace(R.id.container, new FragmentStore()).commit();
            }
        });
        showNewsTextView = (TextView) view.findViewById(R.id.news_account_button);
        showNewsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentTransaction frTransaction =
                        getActivity().getSupportFragmentManager().beginTransaction();
                getActivity().setTitle("Новости");
                frTransaction.replace(R.id.container, new FragmentNews()).commit();
            }
        });

        userProfileName = (TextView) view.findViewById(R.id.user_profile_name);
        userProfileName.setText(User.getUserInstance().getFirstName() + " " +
                User.getUserInstance().getLastName());

        userProfilePhoto = (ImageView) view.findViewById(R.id.user_profile_photo);
        Picasso.get().load(
                ApiUtils.getBaseUrl() + "api/user/viewimage?id=" +
                        User.getUserInstance().getId()).into(userProfilePhoto);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
