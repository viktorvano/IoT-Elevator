# IoT-Elevator
 Using STM32 and ESP8266 to control an elevator with an Android app and with natural speech via SpeechRecognitionAI.  
   
 Video: https://youtu.be/aTKumJoA4KU  
 Speech Recognition AI: https://github.com/viktorvano/SpeechRecognitionAI  
 Similar ESP8266 project (similar STM32 code): https://github.com/viktorvano/Remote-Robot
  
  
### Wiring Diagram  
  
![alt text](https://github.com/viktorvano/IoT-Elevator/blob/main/Documents/schematic.png?raw=true)  
  
  
## Code Snippets
  
[STM32 C code] You have to change your WiFi SSID credentials:  
```C
#define WiFi_Credentials	"AT+CWJAP=\"WiFiSSID\",\"WiFiPASSWORD\"\r\n"
```    
  
[Android Studio - Java]
```Java
public class MainActivity extends AppCompatActivity {

    public static String elevatorIP = "192.168.2.172";//change this IP address according to your ESP8266

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataToServer(elevatorIP, "elevator");
            }
        });
    }

    private void sendDataToServer(String IP, String message)
    {
        try
        {
            // need host and port, we want to connect to the ServerSocket at port 7777
            Socket socket = new Socket();
            socket.setSoTimeout(800);
            socket.connect(new InetSocketAddress(IP, 80), 800);
            System.out.println("Connected!");

            // get the output stream from the socket.
            OutputStream outputStream = socket.getOutputStream();
            // create a data output stream from the output stream so we can send data through it
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            System.out.println("Sending string to the ServerSocket");

            // write the message we want to send
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush(); // send the message
            dataOutputStream.close(); // close the output stream when we're done.

            System.out.println("Closing socket.");
            socket.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
```
  
        
  
### Photos  
###### Elevator  

<img src="https://github.com/viktorvano/IoT-Elevator/blob/main/Documents/IMG_20210726_225514_762.jpg" width="270"/> <img src="https://github.com/viktorvano/IoT-Elevator/blob/main/Documents/IMG_20210726_225547_840.jpg" width="270"/> <img src="https://github.com/viktorvano/IoT-Elevator/blob/main/Documents/IMG_20210726_225614_127.jpg" width="270"/>  
  
    
![alt text](https://github.com/viktorvano/IoT-Elevator/blob/main/Documents/IMG_20210729_152822_182.jpg?raw=true)  
      
###### STM32, ESP8266, step down module and 5V Relay  
![alt text](https://github.com/viktorvano/IoT-Elevator/blob/main/Documents/IMG_20210729_123027_658.jpg?raw=true)  
    
![alt text](https://github.com/viktorvano/IoT-Elevator/blob/main/Documents/IMG_20210729_123042_542.jpg?raw=true)  
    
![alt text](https://github.com/viktorvano/IoT-Elevator/blob/main/Documents/IMG_20210729_123052_021.jpg?raw=true)  
