package edu.uark.spARK;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ClusterView_Fragment extends Fragment {
    public static final String ARG_FRAGMENT_TYPE = "fragment_type";

    public ClusterView_Fragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cluster_view, container, false);
        //
        //int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()), "drawable", getActivity().getPackageName());
        //((ImageView) rootView.findViewById(R.id.image))
        //getActivity().setTitle(planet);
        return rootView;
    }
}
