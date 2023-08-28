#ifndef _ITEM_PARAGRAPH_H_
#define _ITEM_PARAGRAPH_H_

#include <string>
#include <vector>
#include <iostream>
#include "paragraph.h"


class Document;

class Item_Paragraph : public Paragraph{
private:
  std::string _bullet;
public:  
  /*! constructors */
  Item_Paragraph(const Item_Paragraph&);
  Item_Paragraph(std::string ="default_title", std::string c="", std::string b="*");
  Item_Paragraph(std::string b="*");
  virtual ~Item_Paragraph()=default;
  
  virtual Item_Paragraph* clone();
  virtual std::ostream& affiche(std::ostream&) const;

};

#endif
