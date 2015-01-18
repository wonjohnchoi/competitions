################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../DWITE/Question_1.cpp 

OBJS += \
./DWITE/Question_1.o 

CPP_DEPS += \
./DWITE/Question_1.d 


# Each subdirectory must supply rules for building sources it contributes
DWITE/%.o: ../DWITE/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


