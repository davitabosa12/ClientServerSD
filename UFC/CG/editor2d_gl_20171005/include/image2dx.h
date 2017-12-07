#ifndef IMAGE2D_CLASS_H__
#define IMAGE2D_CLASS_H__

typedef unsigned char pixel;
typedef pixel* pixel_row;

class Image
{
public:

	Image();

	// NOVO
	Image( const Image& img );
	// NOVO
	Image& operator=( const Image& img );

	Image( pixel bg, int width, int height );
	
	~Image();
	

	void hline( pixel cor, int y, int x1, int x2 );
	/**
	 *  Desenha uma linha vertical
	 *
	 *    ________x________
	 *
	 *  y1        |
	 *            |
	 *            |
	 *  y2        |
	 *
	 *    __________________
	 */
	void vline( pixel cor, int x, int y1, int y2 );
	void ddaLine(pixel cor, int x1, int y1, int x2, int y2);
	void bresenham(pixel cor, int x1, int y1, int x2, int y2);

	void drawRect( pixel cor, int x1, int y1, int x2, int y2 );
	void fillRect( pixel cor, int x1, int y1, int x2, int y2 );
	void lineMidPoint( pixel cor, int x1, int y1, int x2, int y2  );


	void clear( pixel bgColor =0 );
	void display() const;
	void setPixel( pixel cor, int i, int j );
	void setPixelSafe( pixel cor, int i, int j );
	pixel getPixel( int i, int j ) const;
	pixel getPixelSafe( int i, int j ) const;
	
	pixel* getPixels();
	const pixel* getPixels() const;
	
	int getWidth() const;
	int getHeight() const;
	int getPixelCount() const;
	
	pixel* operator[] (int i)
	{
		return pixels[i];
	}
	const pixel* operator[] (int i) const
	{
		return pixels[i];
	}

private:
	// NOVO
	void _allocate( int w, int h );
	void invertPoint(int &xi, int &yi, int &xf, int &yf);
	// NOVO
	void _deallocate();

	pixel_row* pixels;
	pixel_row  buffer;
	int width;
	int height;
};

#endif

