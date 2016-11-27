package info.metadude.android.brockman.demo.models;

import java.util.List;

import info.metadude.java.library.brockman.models.Stream;
import info.metadude.java.library.brockman.models.Url;
import info.metadude.java.library.brockman.models.VideoSize;

public class StreamViewModelBuilder {

    private String roomDisplay;
    private String roomLink;
    private String roomScheduleName;
    private String roomSlug;
    private String roomThumb;
    private String streamDisplay;
    private boolean streamIsTranslated;
    private String streamSlug;
    private Stream.TYPE streamType;
    private VideoSize streamVideoSize;
    private List<Url> streamUrls;

    public StreamViewModelBuilder setRoomDisplay(String roomDisplay) {
        this.roomDisplay = roomDisplay;
        return this;
    }

    public StreamViewModelBuilder setRoomLink(String roomLink) {
        this.roomLink = roomLink;
        return this;
    }

    public StreamViewModelBuilder setRoomScheduleName(String roomScheduleName) {
        this.roomScheduleName = roomScheduleName;
        return this;
    }

    public StreamViewModelBuilder setRoomSlug(String roomSlug) {
        this.roomSlug = roomSlug;
        return this;
    }

    public StreamViewModelBuilder setRoomThumb(String roomThumb) {
        this.roomThumb = roomThumb;
        return this;
    }

    public StreamViewModelBuilder setStreamDisplay(String streamDisplay) {
        this.streamDisplay = streamDisplay;
        return this;
    }

    public StreamViewModelBuilder setStreamIsTranslated(boolean streamIsTranslated) {
        this.streamIsTranslated = streamIsTranslated;
        return this;
    }

    public StreamViewModelBuilder setStreamSlug(String streamSlug) {
        this.streamSlug = streamSlug;
        return this;
    }

    public StreamViewModelBuilder setStreamType(Stream.TYPE streamType) {
        this.streamType = streamType;
        return this;
    }

    public StreamViewModelBuilder setStreamVideoSize(VideoSize streamVideoSize) {
        this.streamVideoSize = streamVideoSize;
        return this;
    }

    public StreamViewModelBuilder setStreamUrls(List<Url> streamUrls) {
        this.streamUrls = streamUrls;
        return this;
    }

    public StreamViewModel build() {
        return new StreamViewModel(
                roomDisplay,
                roomLink,
                roomScheduleName,
                roomSlug,
                roomThumb,
                streamDisplay,
                streamIsTranslated,
                streamSlug,
                streamType,
                streamVideoSize,
                streamUrls);
    }

}
