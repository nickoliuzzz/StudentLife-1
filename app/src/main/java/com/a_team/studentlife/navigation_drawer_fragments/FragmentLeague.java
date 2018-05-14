package com.a_team.studentlife.navigation_drawer_fragments;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.a_team.studentlife.R;
import com.a_team.studentlife.UserInformation.User;
import com.a_team.studentlife.adapter.leagues.LeaguesAdapter;
import com.a_team.studentlife.card_view_filling.LeagueListElement;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentLeague.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentLeague#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLeague extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private LinearLayoutManager verticalLinearLayoutManager;
    private LeaguesAdapter leaguesAdapter;
    private ProgressBar progressBarSpinner;
    private AnimationDrawable animationDrawable;
    private SwipeRefreshLayout swipe;

    public FragmentLeague() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentLeague.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentLeague newInstance(String param1, String param2) {
        FragmentLeague fragment = new FragmentLeague();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentgetAccessibilityClassName
        final View view = inflater.inflate(R.layout.fragment_league, container, false);
        progressBarSpinner = view.findViewById(R.id.loading_spinner);
        progressBarSpinner.setVisibility(View.VISIBLE);
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.leagueSwipeRefresh);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                createLeagueScreen(view);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        createLeagueScreen(view);
        return view;
    }

    private void createLeagueScreen(View view) {
        recyclerView = view.findViewById(R.id.recycler_list_posts_leagues);
        verticalLinearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(verticalLinearLayoutManager);
        leaguesAdapter = new LeaguesAdapter();
        LeagueListElement.getLeagueListElements(view.getContext(), leaguesAdapter, recyclerView,
                progressBarSpinner, User.getUserInstance().getId(), false);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Название лиги");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText))
                    LeagueListElement.getLeagueListElementsBySearch(getActivity(), leaguesAdapter, recyclerView,
                        progressBarSpinner, User.getUserInstance().getId(), newText);
                else
                    LeagueListElement.getLeagueListElements(getActivity(), leaguesAdapter, recyclerView,
                            progressBarSpinner, User.getUserInstance().getId(), false);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}
