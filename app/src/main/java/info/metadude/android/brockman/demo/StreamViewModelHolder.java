package info.metadude.android.brockman.demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.Space;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import info.metadude.android.brockman.demo.models.StreamViewModel;
import info.metadude.java.library.brockman.models.Url;
import info.metadude.java.library.brockman.models.VideoSize;

class StreamViewModelHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.stream_slug)
    TextView slugTextView;

    @Bind(R.id.stream_display)
    TextView displayTextView;

    @Bind(R.id.stream_type)
    TextView typeTextView;

    @Bind(R.id.stream_is_translated)
    TextView isTranslatedTextView;

    @Bind(R.id.stream_video_size)
    TextView videoSizeTextView;

    @Bind(R.id.room_thumbnail)
    ImageView thumbnailView;

    @Bind(R.id.stream_urls)
    LinearLayout urlsLayout;

    @Bind(R.id.room_link)
    TextView roomLinkTextView;

    @BindString(R.string.translation_available)
    String translationAvailable;

    @BindString(R.string.translation_not_available)
    String translationNotAvailable;

    private Context context;

    private static final LinearLayout.LayoutParams LAYOUT_PARAMS = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);

    StreamViewModelHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    void bind(StreamViewModel streamViewModel) {
        slugTextView.setText(streamViewModel.streamSlug);
        displayTextView.setText(streamViewModel.streamDisplay);
        typeTextView.setText(streamViewModel.streamType.toString());
        isTranslatedTextView.setText(getTranslationText(streamViewModel.streamIsTranslated));
        VideoSize videoSize = streamViewModel.streamVideoSize;
        if (videoSize == null) {
            videoSizeTextView.setVisibility(View.GONE);
        } else {
            videoSizeTextView.setVisibility(View.VISIBLE);
            videoSizeTextView.setText(getVideoSizeText(videoSize));
        }
        String thumbnailUrl = streamViewModel.roomThumb;
        if (TextUtils.isEmpty(thumbnailUrl)) {
            thumbnailView.setVisibility(View.GONE);
        } else {
            Picasso.with(context)
                    .load(thumbnailUrl)
                    .resize(213, 120)
                    .centerCrop()
                    .into(thumbnailView);
            thumbnailView.setVisibility(View.VISIBLE);
        }
        List<Url> urls = streamViewModel.streamUrls;
        urlsLayout.removeAllViews();
        if (urls == null) {
            urlsLayout.setVisibility(View.GONE);
        } else {
            Space space = new Space(context);
            space.setMinimumHeight(30);
            urlsLayout.addView(space);
            LAYOUT_PARAMS.setMargins(0, 10, 0, 0);
            for (Url url : urls) {
                addUrlViews(LAYOUT_PARAMS, url);
            }
            urlsLayout.setVisibility(View.VISIBLE);
        }
        String roomLink = context.getString(R.string.room_link, streamViewModel.roomLink);
        roomLinkTextView.setMovementMethod(LinkMovementMethod.getInstance());
        roomLinkTextView.setText(roomLink);
        Linkify.addLinks(roomLinkTextView, Linkify.WEB_URLS);
    }

    private void addUrlViews(ViewGroup.LayoutParams layoutParams, Url url) {
        TextView displayTextView = new TextView(context);
        displayTextView.setLayoutParams(layoutParams);
        displayTextView.setTextAppearance(context, android.R.style.TextAppearance_Medium);
        displayTextView.setText(url.display);
        urlsLayout.addView(displayTextView);

        TextView techTextView = new TextView(context);
        techTextView.setLayoutParams(layoutParams);
        techTextView.setText(url.tech);
        urlsLayout.addView(techTextView);

        TextView urlTextView = new TextView(context);
        urlTextView.setLayoutParams(layoutParams);
        urlTextView.setMovementMethod(LinkMovementMethod.getInstance());
        urlTextView.setText(url.url);
        Linkify.addLinks(urlTextView, Linkify.WEB_URLS);
        urlsLayout.addView(urlTextView);
    }

    @NonNull
    private String getTranslationText(boolean isTranslated) {
        return isTranslated ? translationAvailable : translationNotAvailable;
    }

    @NonNull
    private String getVideoSizeText(@NonNull VideoSize videoSize) {
        return context.getString(R.string.video_dimensions, videoSize.width, videoSize.height);
    }

}
