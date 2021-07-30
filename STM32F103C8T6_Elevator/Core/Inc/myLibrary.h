/*
 * myLibrary.h
 *
 *  Created on: Dec 20, 2020
 *      Author: vikto
 */

#ifndef INC_MYLIBRARY_H_
#define INC_MYLIBRARY_H_

#define bulgarianVoltageConstant 1.0019f

#include "main.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

extern TIM_HandleTypeDef htim2;
extern TIM_HandleTypeDef htim3;
extern UART_HandleTypeDef huart2;

extern uint8_t buffer[2000];
extern uint16_t buffer_index, timeout, messageHandlerFlag;
extern uint32_t resetCounter;

void SysTickEnable();
void SysTickDisable();

void ESP_RESET();
void ESP_Server_Init();
void ESP_Clear_Buffer();
uint8_t string_compare(char array1[], char array2[], uint16_t length);
int string_contains(char bufferArray[], char searchedString[], uint16_t length);
void messageHandler();

#endif /* INC_MYLIBRARY_H_ */
