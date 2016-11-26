package info.metadude.android.brockman.demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.Space;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import info.metadude.java.library.brockman.models.Stream;
import info.metadude.java.library.brockman.models.Url;
import info.metadude.java.library.brockman.models.VideoSize;

public class StreamViewHolder extends RecyclerView.ViewHolder {

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

    @Bind(R.id.stream_urls)
    LinearLayout urlsLayout;

    @BindString(R.string.translation_available)
    String translationAvailable;

    @BindString(R.string.translation_not_available)
    String translationNotAvailable;

    Context context;

    static final LinearLayout.LayoutParams LAYOUT_PARAMS = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);

    public StreamViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    public void bind(Stream stream) {
        slugTextView.setText(stream.slug);
        displayTextView.setText(stream.display);
        typeTextView.setText(stream.type.toString());
        isTranslatedTextView.setText(getTranslationText(stream.isTranslated));
        VideoSize videoSize = stream.videoSize;
        if (videoSize == null) {
            videoSizeTextView.setVisibility(View.GONE);
        } else {
            videoSizeTextView.setVisibility(View.VISIBLE);
            videoSizeTextView.setText(getVideoSizeText(videoSize));
        }
        List<Url> urls = stream.urls;
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
        return videoSize.width + " x " + videoSize.height;
    }

}
