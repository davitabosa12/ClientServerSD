/*
 * clipping.cpp
 *
 *  Created on: 6 de nov de 2017
 *      Author: davi
 */
#include "clipping.h"

Clipping::Clipping(){

}
int Clipping::getCode(int x, int y, int img_w, int img_h){
	int cod = DENTRO;

	if(x < 0) cod |= ESQUERDA;
	else if(x >= img_w) cod |= DIREITA;

	if(y < 0) cod |= ACIMA;
	else if(y >= img_h) cod |= ABAIXO;

	return cod;
}

bool Clipping::clip(int &x1, int &y1, int &x2, int &y2, int img_w, int img_h){
	int code1 = getCode(x1,y1,img_w,img_h);
	int code2 = getCode(x2,y2,img_w,img_h);

	bool desenha = false;

	while(true){
		if(!(code1 | code2)){
			desenha = true;
			std::cout << "Clipper says: PODE DESENHAR" << std::endl;
			return desenha;
		} else if(code1 & code2){
			std::cerr << "Clipper says: DESENHA NAO VEI" << std::endl;
			return desenha;
		} else{
			int x, y;
			int delta_x = x2 - x1;
			int delta_y = y2 - y1;

			int pontoFora = code1 ? code1 : code2;

			if(pontoFora & ABAIXO){
				x = x1 + delta_x * (img_h-1 -y1) / delta_y;
				y = img_h -1;
			}
			else if(pontoFora & ACIMA){
				x = x1 + delta_x * -y1 / delta_y;
				y = 0;
			}
			else if(pontoFora & DIREITA){
				y = y1 + delta_y * (img_w - 1 - x1) / delta_x;
				x = img_w -1;
			}
			else if(pontoFora & ESQUERDA){
				y = y1 + delta_y * -x1 / delta_x;
				x = 0;
			}

			if(pontoFora == code1){
				x1 = x;
				y1 = y;
				code1 = getCode(x1,y1,img_w,img_h);
			}
			else if(pontoFora == code2){
				x2 = x;
				y2 = y;
				code2 = getCode(x2,y2,img_w,img_h);
			}

		}

	}
	//return desenha;
}



