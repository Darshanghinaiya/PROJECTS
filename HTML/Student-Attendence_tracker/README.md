# Student Attendance Tracker

A browser-based attendance management system built using HTML, Tailwind CSS, and JavaScript. This application allows users to manage courses, enroll students, mark attendance, and generate reports with local file storage support.

---

## Overview

The Student Attendance Tracker is designed to simplify attendance management without requiring any backend or database. All data is stored locally in a JSON file using the browser’s File System Access API.

---

## Features

### Course Management
- Add new courses
- View total lectures conducted
- Delete courses (removes related attendance data)

### Student Management
- Enroll students with Roll Number and Name
- Prevent duplicate roll numbers
- View and delete students

### Attendance Tracking
- Mark attendance (Present / Absent) using toggle switch
- Select course and date before marking attendance
- Prevent duplicate attendance entries for the same date
- Automatically updates lecture count

### Reports
- Generate reports for:
  - Individual courses
  - All courses
- Optional date-based filtering
- Displays:
  - Total Present
  - Total Absent
  - Attendance Percentage
- Highlights students with attendance below 75%

### File Handling
- Create or select a `.json` file
- Load existing data
- Save all data locally
- Persistent storage without backend

---

## UI Features

- Responsive design using Tailwind CSS
- Clean dark theme interface
- Tab-based navigation:
  - Courses
  - Students
  - Attendance
  - Reports
- Interactive toggle for marking attendance

---

## Technologies Used

- HTML5
- Tailwind CSS
- JavaScript (ES6)
- File System Access API

