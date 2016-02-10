package info.metadude.android.brockman.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import info.metadude.java.library.brockman.ApiModule;
import info.metadude.java.library.brockman.StreamsService;
import info.metadude.java.library.brockman.models.Offer;
import info.metadude.java.library.brockman.models.Room;
import info.metadude.java.library.brockman.models.Stream;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.offers_recycler_view)
    RecyclerView mOffersRecyclerView;

    @Bind(R.id.offers_empty)
    TextView mOffersEmptyView;

    private StreamsService mStreamsService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_launcher);
        }

        mOffersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mOffersRecyclerView.setHasFixedSize(true);
        toggleVisibility(null, null);
        initStreamsService();
        fetchOffers();
    }

    private void initStreamsService() {
        OkHttpClient okHttpClient = new OkHttpClient();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            okHttpClient.networkInterceptors().add(httpLoggingInterceptor);
        }
        mStreamsService = ApiModule.provideStreamsService(
                BuildConfig.STREAMING_API_BASE_URL_DEBUG, okHttpClient);
    }

    private void fetchOffers() {
        Call<List<Offer>> offersCall = mStreamsService.getOffers(
                BuildConfig.STREAMING_API_OFFERS_PATH_DEBUG
        );
        offersCall.enqueue(new Callback<List<Offer>>() {
            @Override
            public void onResponse(Response<List<Offer>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    onGetOffersResponseSuccess(response.body());
                } else {
                    com.squareup.okhttp.Response raw = response.raw();
                    onGetOffersResponseFailure("HTTP " + raw.code() + ": " + raw.message());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                onCallbackFailure(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    private void onGetOffersResponseSuccess(List<Offer> offers) {
        updateOffersView(offers);
        toggleVisibility(offers, null);
    }

    private void onGetOffersResponseFailure(@NonNull String message) {
        toggleVisibility(null, message);
    }

    private void onCallbackFailure(String message) {
        toggleVisibility(null, message);
    }

    private void updateOffersView(List<Offer> offers) {
        if (offers == null) {
            return;
        }
        List<Stream> streams = new ArrayList<>(10);
        for (Offer offer : offers) {
            for (Room room : offer.rooms) {
                if (room.streams != null) {
                    for (Stream stream : room.streams) {
                        streams.add(stream);
                    }
                }
            }
        }
        StreamsAdapter offersAdapter = new StreamsAdapter(streams);
        mOffersRecyclerView.setAdapter(offersAdapter);
    }

    private void toggleVisibility(@Nullable List<Offer> offers, @Nullable String errorMessage) {
        if (offers == null || offers.isEmpty()) {
            mOffersRecyclerView.setVisibility(View.GONE);
            if (!TextUtils.isEmpty(errorMessage)) {
                mOffersEmptyView.setText(errorMessage);
            } else {
                mOffersEmptyView.setText(R.string.no_data_default);
            }
            mOffersEmptyView.setVisibility(View.VISIBLE);
        } else {
            mOffersRecyclerView.setVisibility(View.VISIBLE);
            mOffersEmptyView.setVisibility(View.GONE);
        }
    }

}
