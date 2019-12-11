package com.example.mms.main_fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mms.R;
import com.example.mms.activity.CartActivity;
import com.example.mms.activity.Home2Activity;
import com.example.mms.activity.MovieActivity;
import com.example.mms.activity.SearchActivity;
import com.example.mms.adapter.MMSDealsAdapter;
import com.example.mms.dao.ProductCartDAO;
import com.example.mms.dao.ProductDAO;
import com.example.mms.interfaces.HomeView;
import com.example.mms.model.Product;
import com.example.mms.movieactivity.ActionMovie;
import com.example.mms.movieactivity.ComedyMovie;
import com.example.mms.movieactivity.DocumentMovie;
import com.example.mms.movieactivity.HorrorActivity;
import com.example.mms.movieactivity.RomanticMovie;
import com.example.mms.movieactivity.ScienceMovie;
import com.example.mms.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeView {

    private ViewFlipper viewFlipper;
    private GridLayoutManager gridLayoutManager;
    private ProductDAO productDAO;
    private MMSDealsAdapter mmsDealsAdapter;
    private List<Product> productList;
    private RecyclerView recyclerView;
    private ImageButton btnCart;
    private TextView tvNumberInCart;
    private ProductCartDAO productCartDAO;
    private BroadcastReceiver broadcastReceiver;
    private Button btnSearch;
    private TextView tvMovieActivity;
    private TextView tvMusicActivity;
    private LinearLayout llMovieActivity;

    private LinearLayout llHorrorFilm;
    private LinearLayout llComedyFilm;
    private LinearLayout llActionFilm;
    private LinearLayout llScienceFilm;
    private LinearLayout llRomanticFilm;
    private LinearLayout llDocumentFilm;
    private TextView tvNEXTT;

    private HomePresenter homePresenter ;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        FragmentHomeBinding fragmentHomeBinding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_home);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        homePresenter = new HomePresenter(this);
        int images[] = {R.drawable.banner1, R.drawable.banner3, R.drawable.banner4};
        for (int image : images) {
            flipperImages(image);
        }



//
//        fragmentHomeBinding.setNext(homePresenter);
//        fragmentHomeBinding.setCart(homePresenter);
//        fragmentHomeBinding.setSearch(homePresenter);
//        fragmentHomeBinding.setHorrorPop(homePresenter);
//        fragmentHomeBinding.setComedyBolero(homePresenter);
//        fragmentHomeBinding.setActionRock(homePresenter);
//        fragmentHomeBinding.setScienceChildren(homePresenter);
//        fragmentHomeBinding.setRomanticEDM(homePresenter);
//        fragmentHomeBinding.setDocumentIndie(homePresenter);
//        fragmentHomeBinding.setMusicMovie(homePresenter);


        return view;


    }

    public void flipperImages(int image) {
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(2500);
        viewFlipper.setAutoStart(true);

        //
        viewFlipper.setInAnimation(getContext(), android.R.anim.fade_in);
    }

    private void initView(View view) {

        viewFlipper = view.findViewById(R.id.vpSlider);

        tvNEXTT = (TextView) view.findViewById(R.id.tvNEXTT);
        tvNEXTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.Next();
            }
        });



        llHorrorFilm = (LinearLayout) view.findViewById(R.id.llHorrorFilm);
        llHorrorFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.Horror_Pop();
            }
        });

        llComedyFilm = (LinearLayout) view.findViewById(R.id.llComedyFilm);
        llComedyFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.Comedy_Bolero();
            }
        });


        llActionFilm = (LinearLayout) view.findViewById(R.id.llActionFilm);
        llActionFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.Action_Rock();
            }
        });



        llScienceFilm = (LinearLayout) view.findViewById(R.id.llScienceFilm);
        llScienceFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.Science_Children();
            }
        });


        llRomanticFilm = (LinearLayout) view.findViewById(R.id.llRomanticFilm);
        llRomanticFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.Romantic_EDM();
            }
        });



        llDocumentFilm = (LinearLayout) view.findViewById(R.id.llDocumentFilm);
        llDocumentFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.Document_Indie();
            }
        });



        llMovieActivity = (LinearLayout) view.findViewById(R.id.llMovieActivity);


        tvMovieActivity = (TextView) view.findViewById(R.id.tvMovieActivity);



        tvMusicActivity = (TextView) view.findViewById(R.id.tvMusicActivity);
        tvMusicActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.Music_Movie();
            }
        });



        btnSearch = view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.Search();
            }
        });

        tvNumberInCart = view.findViewById(R.id.tvNumberInCart);
        productCartDAO = new ProductCartDAO(getContext());
        tvNumberInCart.setText(String.valueOf(productCartDAO.getNumberInCart(getRootUsername())));

        //Đăng kí BroadcastReceiver để nhận update từ giỏ hàng
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (tvNumberInCart != null)
                    tvNumberInCart.setText(String.valueOf(productCartDAO.getNumberInCart(getRootUsername())));
            }
        };
        IntentFilter intentFilter = new IntentFilter("update");
        getActivity().registerReceiver(broadcastReceiver, intentFilter);


        btnCart = view.findViewById(R.id.btnCart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.Cart();
            }
        });

        recyclerView = view.findViewById(R.id.rvWakaDeal);
        productList = new ArrayList<>();
        productList.clear();
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        productDAO = new ProductDAO(getContext());
        productList = productDAO.getAllProduct("Hot");
        mmsDealsAdapter = new MMSDealsAdapter(getContext(), productList);
        recyclerView.setAdapter(mmsDealsAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        mmsDealsAdapter.notifyDataSetChanged();

    }

    private String getRootUsername() {
        String name;
        name = getContext().getSharedPreferences("USER", getActivity().MODE_PRIVATE).getString("NAME", null);
        return name;
    }

    @Override
    public void onDestroy() {
        try {
            if (broadcastReceiver != null)
                getContext().unregisterReceiver(broadcastReceiver);
        } catch (Exception e) {
        }
        super.onDestroy();
    }


    @Override
    public void Next() {
        startActivity(new Intent(getContext(), MovieActivity.class));
    }

    @Override
    public void Horror_Pop() {
        startActivity(new Intent(getContext(), HorrorActivity.class));
    }

    @Override
    public void Comedy_Bolero() {
        startActivity(new Intent(getContext(), ComedyMovie.class));
    }

    @Override
    public void Action_Rock() {
        startActivity(new Intent(getContext(), ActionMovie.class));
    }

    @Override
    public void Science_Children() {
        startActivity(new Intent(getContext(), ScienceMovie.class));
    }

    @Override
    public void Romantic_EDM() {
        startActivity(new Intent(getContext(), RomanticMovie.class));
    }

    @Override
    public void Document_Indie() {
        startActivity(new Intent(getContext(), DocumentMovie.class));
    }

    @Override
    public void Search() {
        startActivity(new Intent(getContext(), SearchActivity.class));
    }

    @Override
    public void Music_Movie() {
        startActivity(new Intent(getContext(), Home2Activity.class));
    }

    @Override
    public void Cart() {
        Intent intent = new Intent(getContext(), CartActivity.class);
        startActivity(intent);
    }


}
