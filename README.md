# Java Thread Factory Project

## Overview
This Java project demonstrates a custom thread pool implementation using a thread factory pattern. It includes a main class (`Example`) that utilizes a `ThreadFactory` to manage multiple threads efficiently. The project showcases how to execute a list of tasks in parallel using a fixed number of threads, ensuring optimal use of system resources.

## Files
- **Example.java**: Contains the `main` method demonstrating the usage of the `ThreadFactory` to execute tasks in parallel.
- **ThreadFactory.java**: Implements the thread factory which initializes a specified number of worker threads and distributes tasks among them.
- **WorkerThread.java**: Defines the worker threads that execute the tasks provided by the thread factory.

## Features
- Initialization of a customizable number of threads for task execution.
- Execution of tasks in parallel with a simple interface.
- Safe shutdown of threads ensuring all tasks are completed before termination.

## How It Works
- **Initialization**: The `ThreadFactory` is initialized with a specified number of threads. Each thread starts and waits for tasks to execute.
- **Task Execution**: Tasks are distributed among the worker threads in a round-robin fashion to ensure even workload distribution.
- **Shutdown**: The factory waits for all tasks to complete before safely shutting down the threads.

## Customization
You can customize the number of threads by modifying the argument passed to the `ThreadFactory` constructor in `Example.java`.

