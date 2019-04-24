package com.dmma.diploma.opencv;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FindMoves {
    private VideoCapture capture = new VideoCapture();

    private int sensivity = 30;
    private double maxArea = 30;
    private int index = 0;
    public String s = "";
    private Mat frameMat = new Mat(720, 1280, CvType.CV_8UC3);
    private Mat frame_current = new Mat(720, 1280, CvType.CV_8UC3);
    private Mat frame_previous = new Mat(720, 1280, CvType.CV_8UC3);
    private Mat frame_result = new Mat(720, 1280, CvType.CV_8UC3);
    private Size size = new Size(3, 3);


    public FindMoves(VideoCapture capture) {
        this.capture = capture;
    }

    public boolean isMovesFound(Mat frameMat) {
        frameMat.copyTo(frame_current);

        Imgproc.GaussianBlur(frame_current, frame_current, size, 0);

        if (index > 1) {
            Core.subtract(frame_previous, frame_current, frame_result);
            Imgproc.cvtColor(frame_result, frame_result, Imgproc.COLOR_RGB2GRAY);
            Imgproc.threshold(frame_result, frame_result, sensivity, 255, Imgproc.THRESH_BINARY);
            List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
            Imgproc.findContours(frame_result, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

            boolean found = false;
            for (int idx = 0; idx < contours.size(); idx++) {
                Mat contour = contours.get(idx);
                double contourarea = Imgproc.contourArea(contour);
                if (contourarea > maxArea) {
                    found = true;

                    Rect r = Imgproc.boundingRect(contours.get(idx));
                    Scalar scalar1 = new Scalar(0, 0, 255);
                    Scalar scalar2 = new Scalar(0, 255, 0);
                    Imgproc.drawContours(frameMat, contours, idx, scalar1);
                    Imgproc.rectangle(frameMat, r.br(), r.tl(), scalar2, 1);
                }
                contour.release();
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

            if (found) {
                System.out.println("Moved " + s);
                return true;
            } else {
                Imgcodecs.imwrite("snapshots/" + s + ".jpg", frameMat);
                return false;
            }
        }

        index++;

        frame_current.copyTo(frame_previous);
        frameMat.release();
        frame_result.release();
        frame_current.release();

        return false;
    }

}
