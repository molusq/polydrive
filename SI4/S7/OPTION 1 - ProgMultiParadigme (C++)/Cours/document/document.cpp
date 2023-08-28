#include "document.h"
  
  
/*!
 * \file document.cpp 
 *
 * \brief Implementation of class Document.
 *
 * \note Just a simple and non exhaustive example
 */


/*!
 * There is nothing special here.
 */
  Document::Document(std::string title, std::vector<std::string> new_authors, std::vector<Paragraph*> new_paragraphs, std::vector<Paragraph*> new_external_paragraphs)
  {
   _title = title;
   _authors = new_authors;
   
   for (Paragraph* toBeCopied :new_paragraphs){
       Paragraph* copiedPaqragraph = toBeCopied->clone();
       copiedPaqragraph->set_owner(*this);
	_paragraphs.push_back(copiedPaqragraph);
   }
   
   _external_paragraphs = new_external_paragraphs;
  }
  
  /*!
 * There is nothing special here.
 */
  Document::Document(const Document& d)
  {
   _title = d._title;
   _authors = d._authors;
   
      for (unsigned int i = 0; i < d._paragraphs.size(); ++i)
	_paragraphs.push_back(d._paragraphs.at(i)->clone());
   
   _external_paragraphs = d._external_paragraphs;
  }
  
 /*!
 * We destroy the contained paragraph (created by clone)
 */
  Document::~Document()
  {
      for (unsigned int i = 0; i < _paragraphs.size(); ++i){
	delete _paragraphs.at(i);
	}
  }
  
  
  
/*!
 * append add content to the document. 
 *   \param p paragraph: the paragraph to add to the document
 *   \param owned boolean: if true, the paragraph is add to the document as internal resource, otherwise, as external resource
 */
  void Document::append(Paragraph& p, bool owned)
  {
    if (owned == true)
    {
      _paragraphs.push_back((p.clone()));
    }
    else
    {
      _external_paragraphs.push_back(&p);
    }
  }
  
  std::ostream& operator<<(std::ostream& os, const Document& d)
  {
    os << d._title << " (";
    
    for(const std::string author : d._authors){
        os << author << ' ';
    }
    	
    os << ")\n";
    for (Paragraph* ptr_p : d._paragraphs)
	os << *(ptr_p);
	
    for (Paragraph* eptr_p : d._external_paragraphs)
	os << *(eptr_p);
    
    return os;
  }



Paragraph& Document::operator[](unsigned int n) /*throw(No_such_paragraph)*/
{
  if ( n >= (_paragraphs.size() + _external_paragraphs.size()) )
      throw No_such_paragraph();
      
  if ( n < _paragraphs.size() )
  {
    return *(_paragraphs.at(n));
  }
  else
  {
    return *(_external_paragraphs.at( n - _paragraphs.size() ));
  }
  
}
