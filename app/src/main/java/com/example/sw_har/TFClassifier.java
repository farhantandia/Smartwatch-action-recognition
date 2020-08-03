package com.example.sw_har;

import android.content.Context;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

public class TFClassifier {
    static {
        System.loadLibrary("tensorflow_inference");
    }

    private TensorFlowInferenceInterface inferenceInterface;
    private static final String MODEL_FILE = "file:///android_asset/100_20_8_4_all.pb";
    private static final String INPUT_NODE = "LSTM_1_input";
    private static final String[] OUTPUT_NODES = {"Dense_2/Softmax"};
    private static final String OUTPUT_NODE = "Dense_2/Softmax";
    private static final long[] INPUT_SIZE = {1, 100, 8};
    private static final int OUTPUT_SIZE = 4;

    public TFClassifier(final Context context) {
        inferenceInterface = new TensorFlowInferenceInterface(context.getAssets(), MODEL_FILE);
    }

    public float[] predictProbabilities(float[] data) {
        float[] result = new float[OUTPUT_SIZE];
        inferenceInterface.feed(INPUT_NODE, data, INPUT_SIZE);

        inferenceInterface.run(OUTPUT_NODES);
        inferenceInterface.fetch(OUTPUT_NODE, result);

        return result;
    }

}

