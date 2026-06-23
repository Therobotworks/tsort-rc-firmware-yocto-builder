#!/bin/bash
source /opt/ros/jazzy/setup.bash

export LD_LIBRARY_PATH=/opt/ros/jazzy/lib:$LD_LIBRARY_PATH
#export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/usr/local/lib
export ROS_AUTOMATIC_DISCOVERY_RANGE=LOCALHOST

# Launch the robot
exec ros2 launch mustangcontrol_py boot.launch.py
