/*
 * Point.cpp
 *
 *  Created on: 10 de out de 2017
 *      Author: aluno
 */

#include <point.h>

Point::Point() {
	// TODO Auto-generated constructor stub
	x = 0;
	y = 0;

}

Point::Point(int x, int y){
	this->x = x;
	this->y = y;
}

Point::~Point() {
	// TODO Auto-generated destructor stub
}

Point Point::add(Point p ){
	return Point(x + p.x, y + p.y);
}
Point Point::subtr(Point p ){
	return Point(x - p.x, y - p.y);
}

Point Point::operator*(int rhs){
	return Point(x * rhs, y * rhs);
}
int Point::getX(){return x;}
int Point::getY(){return y;}
