#include "item_paragraph.h"
/*!
 * \file paragraph.cpp 
 *
 * \brief Implementation of class Paragraph.
 *
 * \note Just a simple and non exhaustive example
 * \todo add comments
 */

  Item_Paragraph::Item_Paragraph(const Item_Paragraph& p)
    :Paragraph(p)
  {
  std::cout << "Item_Paragraph::Item_Paragraph(const Item_Paragraph& p)" << std::endl;
  _bullet = p._bullet;
  }

  Item_Paragraph::Item_Paragraph(std::string title, std::string content, std::string b)
   :Paragraph(title,content)
  {
  _bullet = b;
  }
  
  Item_Paragraph::Item_Paragraph(std::string b)
  {
  _bullet = b; 
  }

  Item_Paragraph* Item_Paragraph::clone()
  {
    std::cout << "##clone an item_Paragraph##" <<std::endl;
   return new Item_Paragraph(*this);
  }

  std::ostream& Item_Paragraph::affiche(std::ostream& os) const
  {
    os<< "\t \t" << _bullet << " " << _content << std::endl;
    return os;
  }



