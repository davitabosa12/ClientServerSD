/*
 * Point.h
 *
 *  Created on: 10 de out de 2017
 *      Author: aluno
 */

#ifndef SRC_POINT_H_
#define SRC_POINT_H_

class Point {
public:
	Point();
	Point(int x,int y);
	virtual ~Point();
	Point add(Point p);
	Point subtr(Point p);
	Point operator*(int rhs);
	int getX();
	int getY();
private:
	int x;
	int y;
};

#endif /* SRC_POINT_H_ */
