#!/bin/bash

xterm -e java -jar election-exercise.jar consumer p1 3001 &
xterm -e java -jar election-exercise.jar consumer p2 3002 &
xterm -e java -jar election-exercise.jar consumer p3 3003 &
xterm -e java -jar election-exercise.jar consumer p4 3004 &

xterm -e java -jar election-exercise.jar producer p5 3005 &
xterm -e java -jar election-exercise.jar producer p6 3006 &
xterm -e java -jar election-exercise.jar producer p7 3007 &
xterm -e java -jar election-exercise.jar producer p8 3008