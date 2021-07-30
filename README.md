# IoT-Elevator
 Using STM32 and ESP8266 to control an elevator with an Android app and with natural speech via SpeechRecognitionAI.  
  
  
### Wiring Diagram  
  
![alt text](https://github.com/viktorvano/IoT-Elevator/blob/main/Documents/schematic.png?raw=true)  
  
  
## Code Snippets
  
[STM32 C code] You have to change your WiFi SSID credentials:  
```C
void ESP_Server_Init()
{
	ESP_RESET();
	HAL_Delay(2000);
	ESP_Clear_Buffer();

	HAL_UART_Transmit(&huart1, (uint8_t*)"AT+RST\r\n", strlen("AT+RST\r\n"), 100);
	HAL_Delay(1500);
	ESP_Clear_Buffer();

	HAL_UART_Transmit(&huart1, (uint8_t*)"AT+CWMODE=1\r\n", strlen("AT+CWMODE=1\r\n"), 100);
	HAL_Delay(2000);
	ESP_Clear_Buffer();

	HAL_UART_Transmit(&huart1, (uint8_t*)"AT+CWDHCP=1,1\r\n", strlen("AT+CWDHCP=1,1\r\n"), 100);
	HAL_Delay(2000);
	ESP_Clear_Buffer();

	HAL_UART_Transmit(&huart1, (uint8_t*)"AT+CIPMUX=1\r\n", strlen("AT+CIPMUX=1\r\n"), 100);
	HAL_Delay(2000);
	ESP_Clear_Buffer();

	HAL_UART_Transmit(&huart1, (uint8_t*)"AT+CIPSERVER=1,80\r\n", strlen("AT+CIPSERVER=1,80\r\n"), 100);
	HAL_Delay(2000);
	ESP_Clear_Buffer();

 //Change your WiFi SSID credentials below
	HAL_UART_Transmit(&huart1, (uint8_t*)"AT+CWJAP=\"WiFiSSID\",\"WiFiPASSWORD\"\r\n", strlen("AT+CWJAP=\"WiFiSSID\",\"WiFiPASSWORD\"\r\n"), 100);
}

//and also here:
void messageHandler()
{
	...else if(string_contains((char*)buffer, "+CWJAP:", buffer_index) != -1
			&& (string_contains((char*)buffer, "FAIL", buffer_index) != -1
			|| string_contains((char*)buffer, "DISCONNECT", buffer_index) != -1))
	{
		//Change your WiFi SSID credentials below
		HAL_UART_Transmit(&huart1, (uint8_t*)"AT+CWJAP=\"WiFiSSID\",\"WiFiPASSWORD\"\r\n", strlen("AT+CWJAP=\"WiFiSSID\",\"WiFiPASSWORD\"\r\n"), 100);
	}
	ESP_Clear_Buffer();
	__HAL_UART_ENABLE_IT(&huart1, UART_IT_RXNE);
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
