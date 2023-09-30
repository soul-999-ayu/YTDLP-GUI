# YT-DLP-GUI
It is a graphics based program that can be used to download video from YouTube at any available quality. This program uses YT-DLP as backend and hence the name. This program can download videos in both windows and linux without any modification or changes. The code is written in JAVA and is in initial stage.

# Features:
* Can check if dependecies are installed or not.
* Can download video in both Windows and Linux.
* Can Download video in any quality.
* Shows download percentage.


# Screenshots:
<details>
  <summary>Click here to view</summary>
  <img src="https://github.com/DeadSOUL-Studios/YT-DLP-GUI/assets/119154806/4571ae58-57ff-4b47-875a-7e35aed12475" name="Screenshot (1)">
  <img src="https://github.com/DeadSOUL-Studios/YT-DLP-GUI/assets/119154806/89babf2c-1af1-4633-8777-763699c428ab" name="Screenshot (2)">
  <img src="https://github.com/DeadSOUL-Studios/YT-DLP-GUI/assets/119154806/8e75ea6b-af4d-4d14-89e6-4ab0043af206" name="Screenshot (3)">
  <img src="https://github.com/DeadSOUL-Studios/YT-DLP-GUI/assets/119154806/666dbd58-8cb5-4e59-ac81-fd133d9ceabc" name="Screenshot (4)">
  <img src="https://github.com/DeadSOUL-Studios/YT-DLP-GUI/assets/119154806/1a411e5e-8a4e-4c82-976b-2006b9236c3f" name="Screenshot (5)">
  <img src="https://github.com/DeadSOUL-Studios/YT-DLP-GUI/assets/119154806/f3085f14-1fd1-4b55-84f9-0b868f2c2cbf" name="Screenshot (6)">
  <img src="https://github.com/DeadSOUL-Studios/YT-DLP-GUI/assets/119154806/d4c213d7-f5c8-44d1-a499-ce51f5eb8db6" name="Screenshot (7)">
  <img src="https://github.com/DeadSOUL-Studios/YT-DLP-GUI/assets/119154806/8b188f42-65dd-4246-81cc-4f4ab757324f" name="Screenshot (8)">
</details>


# Dependencies:
### JRE or JDK 17(+)
Note that JRE or JDK version 17 or above must be installed in order to run the program properly in both linux and windows.

## Windows (Paste all the codes in Command Prompt):
#### Choco (to install dependencies):
```
@"%SystemRoot%\System32\WindowsPowerShell\v1.0\powershell.exe" -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command "[System.Net.ServicePointManager]::SecurityProtocol = 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))" && SET "PATH=%PATH%;%ALLUSERSPROFILE%\chocolatey\bin"
```
#### YT-DLP:
```
choco install yt-dlp -y
```
#### FFMPEG:
```
choco install ffmpeg -y
```
Having issues installing dependencies? follow [this tutorial](https://youtu.be/FkiwVVWhSys).

#
## Linux (Paste all the codes in Terminal):
#### YT-DLP:
```
sudo curl -L https://github.com/yt-dlp/yt-dlp/releases/latest/download/yt-dlp -o /usr/local/bin/yt-dlp
```
```
sudo chmod a+rx /usr/local/bin/yt-dlp
```
#### FFMPEG (if not installed):
```
sudo apt install ffmpeg
```
#### Ink-Free Font (optional but recommended):
Download the font from here: [Ink-Free](https://www.dafontfree.co/wp-content/uploads/2022/02/Ink-Free-Font.zip)

Open Terminal and paste:
```
sudo apt install unzip
```
```
cd Downloads/
```
```
unzip Ink-Free-Font.zip
```
```
sudo mkdir /usr/share/fonts/truetype/"Ink free"
```
```
sudo cp Inkfree.ttf /usr/share/fonts/truetype/"Ink free"/
```

# How to use:
## Windows:
Simply run the exe file, paste the video link and download it with ease. Note that some videos might take long to get loaded or downloaded and some videos might not get downloaded at all, we'll try to fix all the issues in the future.
## Linux:
Download the jar file, open terminal and type:
```
cd Downloads/
```
```
java -jar YTDLP-GUI.jar
```

# Changelogs v2:
* Added tooltips (Show details while hovering over buttons).
* Added info page to show our details.
* Added a settings page to change Theme, and other settings.
* Added the ability to remember theme and position where software was previously closed.
* Added downloading thumbnail and subtitle with video option.
* Added Download folder chooser (in settings page).
* Added functionality to download shorts.
* Added "Exit Confirmation" dialog.
* Changed logic to load Title (Fixed many issues).

# Issues:
* Sometimes it takes a lot of time to load video details.
* The title won't show characters of any language other than English (If Ink free is used).
* Title sometimes shows wrong data.
* Screen flickers to update the download percentage.

# Future plans:
* We'll add features like cancel download, concurrent download and try to fix all the issues that are currently present in this version.

# Credits:
* [yt-dlp](https://github.com/yt-dlp/yt-dlp) is licensed under The Unlicense
* [FFmpeg](https://www.ffmpeg.org/) is licensed under GNU LGPL v2.1

# Conclusion:
This program's UI is Amazing and the code is short as well. There are some limitations which are discussed above. You can get the code from here and Jar and Exe file from release section of this repo.

Thanks for reading till end, please consider checking my other repos as well.
