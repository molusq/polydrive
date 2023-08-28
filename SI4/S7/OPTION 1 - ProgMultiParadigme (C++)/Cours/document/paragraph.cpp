#include "paragraph.h"
/*!
 * \file paragraph.cpp 
 *
 * \brief Implementation of class Paragraph.
 *
 * \note Just a simple and non exhaustive example
 */

Paragraph::Paragraph(const Paragraph& p)
:_title(p._title), 
 _content(p._content),
 _owner(p._owner)
{}


Paragraph::Paragraph(std::string title, std::string content)
:_title(title),
 _content(content),
 _owner(nullptr)
{}
  

Paragraph::Paragraph(Document& owner, std::string title, std::string content)
:_title(title),
 _content(content),
 _owner(&owner)
{}


/*
 * or...
Paragraph::Paragraph(Document& owner, std::string title, std::string content)
:Paragraph(title,content)
{
    _owner = &owner;
}
*/


  
/*!
 *  get_owner, acessor to provate content
 *   \return the owner of the paragraph
 */
  Document& Paragraph::get_owner()
  {
  return *_owner;
  }
  
  /*!
  set_owner, acessor to provate content
    \param _new_owner the owner of the paragraph
  */
  void Paragraph::set_owner(Document& new_owner)
  {
  _owner = &new_owner;
  }

  Paragraph& Paragraph::operator=(const Paragraph& p)
  {
    _title = std::string(p._title);
    _content = std::string(p._content);
    _owner = p._owner;  
    return *this;
  }


/*!
 *  \ref _title is merged with p._title and separate by a slash ('/')
 *  \ref _content is simply merged with p._content without separation character
 *  \param p the Paragraph to concatenate with this
 */
  Paragraph& Paragraph::operator+=(const Paragraph& p) {
    if (this == &p)
	return *this;
    this->_title += (" / " + p._title);
    this->_content += p._content;
    return *this;
  }

  Paragraph* Paragraph::clone()
  {
   std::cout << "##clone a Paragraph##" <<std::endl;
   return new Paragraph(*this);
  }


  std::ostream& Paragraph::affiche(std::ostream& os) const
  {
    return os<< '\t' << _title << std::endl << "\t\t" << _content << std::endl;
  }

  std::ostream& operator<<(std::ostream& os, const Paragraph& p)
  {
    return p.affiche(os);
  }



/*!
 *  operator +=, concatenate two instances of Paragraph to create a new one. 
 *  \ref _title is merged with p._title and separate by a slash ('/')
 *  \ref _content is simply merged with p._content without separation character
 *  \param p1 the first Paragraph to merge
 *  \param p2 the first Paragraph to merge
 *  \return Paragraph the resulting Paragraph, i.e. the merged one
 */
Paragraph operator+(const Paragraph& p1, const Paragraph& p2) {
 return Paragraph(p1._title+" / "+p2._title, p1._content + p2._content);
}




