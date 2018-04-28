package indoor_parking_location_detection_system.accelerometer_gyroscope;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SensorActivity extends AppCompatActivity {

    TextView accel_x,accel_y,accel_z,gyro_x,gyro_y,gyro_z;
    SensorManager sensorManager;
    Sensor accelSensor, gyroSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
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
        sensorManager.registerListener(accelListener, accelSensor, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(gyroListener, gyroSensor, sensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(accelListener);
        sensorManager.unregisterListener(gyroListener);
    }

    SensorEventListener accelListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float acc_x = event.values[0];
            float acc_y = event.values[1];
            float acc_z = event.values[2];

            accel_x.setText("X : "+(int)acc_x);
            accel_y.setText("Y : "+(int)acc_y);
            accel_z.setText("Z : "+(int)acc_z);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    SensorEventListener gyroListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float gy_x = event.values[0];
            float gy_y = event.values[1];
            float gy_z = event.values[2];

            gyro_x.setText("X : "+gy_x);
            gyro_y.setText("X : "+gy_y);
            gyro_z.setText("X : "+gy_z);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
