package info.metadude.android.brockman.demo.models;

import java.util.List;

import info.metadude.java.library.brockman.models.Stream;
import info.metadude.java.library.brockman.models.Url;
import info.metadude.java.library.brockman.models.VideoSize;

public class StreamViewModel {

    // Room part

    public final String roomDisplay;

    public final String roomLink;

    public final String roomScheduleName;

    public final String roomSlug;

    public final String roomThumb;

    // Stream part

    public final String streamDisplay;

    public final boolean streamIsTranslated;

    public final String streamSlug;

    public final Stream.TYPE streamType;

    public final VideoSize streamVideoSize;

    public final List<Url> streamUrls;


    public StreamViewModel(String roomDisplay,
                           String roomLink,
                           String roomScheduleName,
                           String roomSlug,
                           String roomThumb,
                           String streamDisplay,
                           boolean streamIsTranslated,
                           String streamSlug,
                           Stream.TYPE streamType,
                           VideoSize streamVideoSize,
                           List<Url> streamUrls) {
        this.roomDisplay = roomDisplay;
        this.roomLink = roomLink;
        this.roomScheduleName = roomScheduleName;
        this.roomSlug = roomSlug;
        this.roomThumb = roomThumb;
        this.streamDisplay = streamDisplay;
        this.streamIsTranslated = streamIsTranslated;
        this.streamSlug = streamSlug;
        this.streamType = streamType;
        this.streamVideoSize = streamVideoSize;
        this.streamUrls = streamUrls;
    }

}
