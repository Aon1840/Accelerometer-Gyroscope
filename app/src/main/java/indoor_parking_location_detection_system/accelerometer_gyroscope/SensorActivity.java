package indoor_parking_location_detection_system.accelerometer_gyroscope;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.text.DecimalFormat;

public class SensorActivity extends AppCompatActivity {

    TextView accel_x,accel_y,accel_z,gyro_x,gyro_y,gyro_z;
    SensorManager sensorManager;
    Sensor accelSensor, gyroSensor;
    DecimalFormat dcm = new DecimalFormat("0.0000");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        accel_x = findViewById(R.id.accel_x);
        accel_y = findViewById(R.id.accel_y);
        accel_z = findViewById(R.id.accel_z);

        gyro_x = findViewById(R.id.gyro_x);
        gyro_y = findViewById(R.id.gyro_y);
        gyro_z = findViewById(R.id.gyro_z);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(accelListener, accelSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(gyroListener, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(accelListener);
        sensorManager.unregisterListener(gyroListener);
    }

    SensorEventListener accelListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent eventAcc) {
            float acc_x = eventAcc.values[0];
            float acc_y = eventAcc.values[1];
            float acc_z = eventAcc.values[2];

            accel_x.setText("X : "+dcm.format(acc_x));
            accel_y.setText("Y : "+dcm.format(acc_y));
            accel_z.setText("Z : "+dcm.format(acc_z));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    SensorEventListener gyroListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent eventGyro) {
            Log.d("gyroListener","onSensorChanged pass");
            float gy_x = eventGyro.values[0];
            float gy_y = eventGyro.values[1];
            float gy_z = eventGyro.values[2];

            gyro_x.setText("X : "+dcm.format(gy_x));
            gyro_y.setText("Y : "+dcm.format(gy_y));
            gyro_z.setText("Z : "+dcm.format(gy_z));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
