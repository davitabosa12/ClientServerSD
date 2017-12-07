/*
 * clipping.h
 *
 *  Created on: 6 de nov de 2017
 *      Author: davi
 */
#include<iostream>
#ifndef INCLUDE_CLIPPING_H_
#define INCLUDE_CLIPPING_H_


class Clipping{
public:
		Clipping();
		const int ACIMA = 8;
		const int ABAIXO = 4;
		const int DIREITA = 2;
		const int ESQUERDA = 1;
		const int DENTRO = 0;

		int getCode(int x, int y, int img_w, int img_h); //retorna o codigo de onde o ponto esta
		bool clip(int &x1, int &y1, int &x2, int &y2, int img_w, int img_h);
};


#endif /* INCLUDE_CLIPPING_H_ */
