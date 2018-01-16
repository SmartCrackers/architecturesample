package com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Constants;
import com.exception.BookException;
import com.modelUtility.EditableInfo;
import com.models.Book;
import com.repositories.BookRepository;

/**
 * @author RITESH SINGH
 */
@Service("bookService")
@ComponentScan("com.repositories")
public class BookServiceImpl implements BookService {

	@Autowired
	@Qualifier("bookRepository")
	private BookRepository bookRepository;
	
	@Autowired
	@Qualifier("fileUtility")
	private FileUtility fileUtility;
	
	@Override
	public Book save(Book book, MultipartFile coverImage, MultipartFile image) {
		
		this.validateTosave(book);
		this.proccessBookLogoAndImage(book,coverImage,image,false);
		return bookRepository.save(book);
	}

	@Override
	public Long count() {
		return bookRepository.count();
	}

	@Override
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	@Override
	public List<Book> getBooks(Sort sort) {
		return bookRepository.findAll(sort);
	}

	@Override
	public Page<Book> getBooks(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}
	
	@Override
	public Book getBookById(String bookId) {
		return bookRepository.findOne(bookId);
	}

	@Override
	public Book update(Book book, MultipartFile coverImage, MultipartFile image) {
		Book updateBook = this.validateToUpdate(book);
		
		if(!StringUtils.isEmpty(updateBook)){
			this.proccessBookLogoAndImage(updateBook,coverImage,image,true);
			return bookRepository.save(updateBook);
		}
		return null;
	}
	
	/**
	 * <b>This class is used only for utilities process. </b>
	 * @author RITESH SINGH
	 *
	 */
	/*class InnerProccess{*/
		
		private void proccessBookLogoAndImage(Book book, MultipartFile coverImage,MultipartFile image, Boolean isUpdate){
			
			String hashedFileName = null;
			try {
				if (image != null && !image.isEmpty()) {
					if(isUpdate){
						fileUtility.deleteFile(Constants.FOLDER_BOOK, book.getBookCode(), book.getHashedBookImage());
					}
					saveBookImage(book, image, hashedFileName);
					saveBookLogo(book, coverImage, hashedFileName);
				}
				
			} catch (Exception e) {
				fileUtility.deleteFile(Constants.FOLDER_BOOK, book.getBookCode(), hashedFileName);
				throw e;
			}
		}
		
		private void saveBookImage(Book book, MultipartFile image, String hashedFileName){
			
			hashedFileName = fileUtility.getFileName(image.getOriginalFilename());
			fileUtility.saveFile( image, Constants.FOLDER_BOOK, book.getBookCode(),
					hashedFileName);
			book.setBookImage(image.getOriginalFilename());
			book.setHashedBookImage(hashedFileName);
		}
		
		private void saveBookLogo(Book book, MultipartFile coverImage, String hashedFileName){
			hashedFileName = fileUtility.getFileName(coverImage.getOriginalFilename());
			fileUtility.saveFile( coverImage, Constants.FOLDER_BOOK, book.getBookCode(),
					hashedFileName);
			book.setBookCoverImage(coverImage.getOriginalFilename());
			book.setHashedBookCoverImage(hashedFileName);
		}
		
		private void validateTosave(Book book){
			try{
				if(!StringUtils.isEmpty(book)){
					if(book.getBookName()==null){
						String message = String.format("Book-Name can not be blank.");
						throw new BookException(message);
					}
					if(book.getBookCode()==null){
						String message = String.format("Book-Code can not be blank.");
						throw new BookException(message);
					}
					
					Book.Account account = (new Book()).new Account();
					account.setIsDiscount(false);
					account.setMrp(00000D);
					account.setQuantity(00L);
					account.setUnit("XXXX");
					book.setAccount(account);
					book.setIsAvailable(false);
					book.setIsOffer(false);
					EditableInfo editableInfo = new EditableInfo();
					editableInfo.setCreatedBy("Super-Admin");
					editableInfo.setUpdatedBy("Super-Admin");
					book.setEditableInfo(editableInfo);
					
				}
			}catch (Exception e) {
				String message = String.format("Error while validating Book for create.");
				throw new BookException(message, e);
			}
		}
		
		private Book validateToUpdate(Book book){
			try{
				if(!StringUtils.isEmpty(book)){
					if(book.getId()!=null){			
						Book existingBook = getBookById(book.getId());
						if(!StringUtils.isEmpty(existingBook)){
							if(book.getBookName()!=null)
								existingBook.setBookName(book.getBookName());
							if(book.getIsAvailable()!=null)
								existingBook.setIsAvailable(book.getIsAvailable());
							if(book.getIsOffer()!=null)
								existingBook.setIsOffer(book.getIsOffer());
							if(book.getAboutBook()!=null)
								existingBook.setAboutBook(book.getAboutBook());
							if(book.getBookCoverImage()!=null)
								existingBook.setBookCoverImage(book.getBookCoverImage());
							if(book.getBookImage()!=null)
								existingBook.setBookImage(book.getBookImage());
							if(book.getCategory()!=null)
								existingBook.setCategory(book.getCategory());
							if(book.getPublisherName()!=null)
								existingBook.setPublisherName(book.getPublisherName());
							if(book.getSeries()!=null)
								existingBook.setSeries(book.getSeries());
							if(book.getTag()!=null)
								existingBook.setTag(book.getTag());
							if(book.getTotalPages()!=null)
								existingBook.setTotalPages(book.getTotalPages());
							if(book.getType()!=null)
								existingBook.setType(book.getType());
							if(book.getVersion()!=null)
								existingBook.setVersion(book.getVersion());
							if(book.getWritterName()!=null)
								existingBook.setWritterName(book.getWritterName());
						
							EditableInfo editableInfo = existingBook.getEditableInfo();
							editableInfo.setUpdatedAt();
							editableInfo.setUpdatedBy("Super-Admin");
							existingBook.setEditableInfo(editableInfo);
							
							return existingBook;
						}
					}
				}
			}catch (Exception e) {
				String message = String.format("Error while validating Book for update.");
				throw new BookException(message, e);
			}
			
			return null;
		}
	}
/*}*/
