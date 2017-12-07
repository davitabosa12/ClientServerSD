/*
 * filler.h
 *
 *  Created on: 6 de dez de 2017
 *      Author: davi
 */

#ifndef FILLER_H_
#define FILLER_H_

class Filler {
public:
	Filler();
	~Filler();

	void fillConvex(pixel currentColor, pixel newColor, int x, int y);
};

#endif /* FILLER_H_ */
