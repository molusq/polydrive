#ifndef _PARAGRAPH_H_
#define _PARAGRAPH_H_

#include <string>
#include <vector>
#include <iostream>

class Document;

class Paragraph{
private:
  std::string _title;
protected:
  std::string _content;
private:
  Document* _owner;
public:  
  /*! constructors */
  Paragraph(const Paragraph&);
  Paragraph(std::string t="default_title", std::string c=""); //no owner to ease testing phase
  Paragraph(Document& d, std::string t="default_title", std::string c="");
  virtual ~Paragraph(){};
  /*!
  get_owner, acessor to private content. Note that the document is returned by reference
    \return the owner of the paragraph
  */
  Document& get_owner();
  
  /*!
  set_owner, acessor to provate content
    \param _new_owner the owner of the paragraph
  */
  void set_owner(Document& new_owner);
  
  virtual Paragraph* clone();
  
  virtual std::ostream& affiche(std::ostream&) const;
  
  Paragraph& operator=(const Paragraph&);
  Paragraph& operator+=(const Paragraph&);
  
  friend Paragraph operator+(const Paragraph& p1, const Paragraph& p2);
  
  friend std::ostream& operator<<(std::ostream&, const Paragraph&);
  
};

#endif
