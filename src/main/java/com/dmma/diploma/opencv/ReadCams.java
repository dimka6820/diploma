package com.dmma.diploma.opencv;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class ReadCams {
    private VideoCapture capture;
    private FindMoves findMoves;

    Mat frameMat = new Mat(720, 1280, CvType.CV_8UC3);

    public ReadCams(VideoCapture capture, String s) {
        this.capture = capture;
        this.findMoves = new FindMoves(capture);
        findMoves.s = s;
    }

    public void read() {
        if (this.capture.isOpened()) {
            if (capture.read(frameMat)) {
                findMoves.isMovesFound(frameMat);
            }
        }
    }
}
