#ifndef _DOCUMENT_H_
#define _DOCUMENT_H_

#include <string>
#include <vector>
#include <iostream>
#include "paragraph.h"
#include "item_paragraph.h"

/*!
 * \file document.h
 *
 * \brief Definition of class Document, used as an example in the course.
 */
class Document{
private:
  std::string _title;
  std::vector<std::string> _authors;
  std::vector<Paragraph*> _paragraphs;
  std::vector<Paragraph*> _external_paragraphs;
public:  
  //! exception class
  class No_such_paragraph{};

  //! constructor with default parameters
  Document(std::string title="default_title", std::vector<std::string> new_authors= std::vector<std::string>(), std::vector<Paragraph*> new_paragraphs=std::vector<Paragraph*>(), std::vector<Paragraph*> new_external_paragraphs=std::vector<Paragraph*>());

  //! Copy constructor
  Document(const Document&);
  ~Document();

  //! append paragraph to the document
  void append(Paragraph& p, bool owned);
  
  //! subscripting operator
  Paragraph& operator[](unsigned int n); /*throw(No_such_paragraph);*/ //deprecated in C++1, not allowed in C++17
  
  
  friend std::ostream& operator<<(std::ostream&, const Document&);
  
};

#endif
