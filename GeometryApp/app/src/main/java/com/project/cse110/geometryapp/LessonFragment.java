package com.project.cse110.geometryapp;

import android.content.Context;
import android.content.Intent;
//import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.v4.app.Fragment;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {} interface
 * to handle interaction events.
 * Use the {@link LessonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LessonFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "page";
    private Intent activityIntent;
    private ArrayList<String> lessonDescriptions;
    private static Bundle extras;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private int mParam2;
    private int pageNum;

    private String description;

    //private OnFragmentInteractionListener mListener;

    public LessonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LessonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LessonFragment newInstance(Bundle extras) {

        LessonFragment fragment = new LessonFragment();
        fragment.setArguments(extras);

        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        System.out.println("inside OnCreate for Fragment");
//
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getInt(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("Inside onCreateView for fragment");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lesson, container, false);
        //int position = getArguments().getInt("Position");
        description = getArguments().getString("CurrentDescription");

        TextView text = (TextView) view.findViewById(R.id.description);
        text.setText(description);

        int image = getArguments().getInt("CurrentImage");

        // Check if the page should be left blank
        if (image != -1) {
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
            imageView.setImageResource(image);
        }

        return view;
    }

    //@Override
    //public void onActivityCreated()

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

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

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
