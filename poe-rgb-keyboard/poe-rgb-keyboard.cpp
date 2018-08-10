#include "stdafx.h"
#include <windows.h>
#include <stdio.h>
#include <iostream>
#include "SDKDLL.h" 
#include <tchar.h>

BOOL CtrlHandler(DWORD fdwCtrlType)
{
	switch (fdwCtrlType)
	{ 
	case CTRL_C_EVENT:
	case CTRL_CLOSE_EVENT:
		EnableLedControl(false);
		exit(EXIT_SUCCESS);
		return TRUE;
	default:
		return FALSE;
	}
}

POINT getCursorPos() {
	POINT p;
	GetCursorPos(&p);
	return p;
}

COLORREF getColor(POINT p) {
	HDC dc = GetDC(NULL);
	COLORREF color = GetPixel(dc, p.x, p.y);
	ReleaseDC(NULL, dc);
	return color;
}

void debug(POINT p, COLORREF color) {
	printf("pos(%i, %i) rgb(%i, %i, %i)\n", p.x, p.y, GetRValue(color), GetGValue(color), GetBValue(color));
}

void setRowColor(int x, BYTE r, BYTE g, BYTE b) {
	for (int y = 0; y <= 21; y++) {
		SetLedColor(x, y, r, g, b);
	}
}

void checkHealth(POINT points[6]) {
	for (int x = 0; x < 6; x++) {
		COLORREF color = getColor(points[x]);
		if (GetRValue(color) >= 80 && GetGValue(color) <= 40 && GetBValue(color) <= 40) {
			setRowColor(x, 255, 0, 0);
		} else {
			setRowColor(x, 0, 0, 0);
		}
	}
}

bool isPoeActive() {
	HWND window = GetForegroundWindow();
	TCHAR title[256];
	GetWindowText(window, title, sizeof(title));
	return _tcscmp(title, _T("Path of Exile")) == 0;
}

int main()
{
	// Disable mouse clicks
	DWORD prev_mode;
	HANDLE hStdin = GetStdHandle(STD_INPUT_HANDLE);
	GetConsoleMode(hStdin, &prev_mode);
	SetConsoleMode(hStdin, prev_mode & ~ENABLE_QUICK_EDIT_MODE);

	// Register ctrl+c handler
	if (!SetConsoleCtrlHandler((PHANDLER_ROUTINE) CtrlHandler, TRUE))
	{
		printf("[ERROR] Unable to register ctrl handler.");
		system("pause");
		return 0;
	}

	// Keyboard checks
	SetControlDevice(DEV_MKeys_L);
	if (!IsDevicePlug()) {
		printf("Could not find a Coolermaster MasterKeys Pro L keyboard.\nSadly currently no other keyboards are supported.\n");
		system("pause");
		return 0;
	}
	EnableLedControl(true);

	printf("[INFO] Made by swordbeta.com\n");
	printf("[INFO] Succesfully setup everything.\n");
	printf("[INFO] Starting PoE RGB now!\n");

	// Start watching health!
	POINT _1920[6] = { { 88, 884 },{ 88, 920 },{ 88, 950 },{ 88, 980 },{ 88, 1010 },{ 100, 1047 } };
	while (1) {
		if (!isPoeActive()) {
			SetFullLedColor(210, 180, 140);
		} else {
			checkHealth(_1920);
		}
		Sleep(250);
	}
	return 0;
}