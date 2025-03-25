import React, { useState, useEffect } from 'react';
import {
  Box, Typography, Button, TextField,
  Table, TableBody, TableCell, TableContainer,
  TableHead, TableRow, Paper, IconButton,
  Dialog, DialogTitle, DialogContent, DialogActions,
  MenuItem, Select, InputLabel, FormControl, Chip,
  Autocomplete
} from '@mui/material';
import { Add, Edit, Delete } from '@mui/icons-material';
import {
  getBooks, createBook, updateBook, deleteBook,
  getGenres, getAuthors
} from '../services/api';

const BooksPage = () => {
  const [books, setBooks] = useState([]);
  const [genres, setGenres] = useState([]);
  const [authors, setAuthors] = useState([]);
  const [currentBook, setCurrentBook] = useState({
    title: '',
    genreId: '',
    publishYear: '',
    isbn: '',
    copiesAvailable: 0,
    authorIds: []
  });
  const [openDialog, setOpenDialog] = useState(false);
  const [isEditing, setIsEditing] = useState(false);

  useEffect(() => {
    fetchBooks();
    fetchGenres();
    fetchAuthors();
  }, []);

  const fetchBooks = async () => {
    try {
      const response = await getBooks();
      setBooks(response.data);
    } catch (error) {
      console.error('Error fetching books:', error);
    }
  };

  const fetchGenres = async () => {
    try {
      const response = await getGenres();
      setGenres(response.data);
    } catch (error) {
      console.error('Error fetching genres:', error);
    }
  };

  const fetchAuthors = async () => {
    try {
      const response = await getAuthors();
      setAuthors(response.data);
    } catch (error) {
      console.error('Error fetching authors:', error);
    }
  };

  const handleAddBook = async () => {
    if (!currentBook.title.trim() || !currentBook.genreId || currentBook.authorIds.length === 0) return;

    try {
      const bookData = {
        title: currentBook.title,
        genreId: parseInt(currentBook.genreId),
        publishYear: currentBook.publishYear ? parseInt(currentBook.publishYear) : null,
        isbn: currentBook.isbn || null,
        copiesAvailable: parseInt(currentBook.copiesAvailable) || 0,
        authorIds: currentBook.authorIds.map(id => parseInt(id))
      };
      await createBook(bookData);
      resetForm();
      fetchBooks();
    } catch (error) {
      console.error('Error adding book:', error);
    }
  };

  const handleUpdateBook = async () => {
    if (!currentBook.title.trim() || !currentBook.genreId || currentBook.authorIds.length === 0) return;

    try {
      const bookData = {
        title: currentBook.title,
        genreId: parseInt(currentBook.genreId),
        publishYear: currentBook.publishYear ? parseInt(currentBook.publishYear) : null,
        isbn: currentBook.isbn || null,
        copiesAvailable: parseInt(currentBook.copiesAvailable) || 0,
        authorIds: currentBook.authorIds.map(id => parseInt(id))
      };
      await updateBook(currentBook.id, bookData);
      resetForm();
      fetchBooks();
    } catch (error) {
      console.error('Error updating book:', error);
    }
  };

  const handleDeleteBook = async (id) => {
    try {
      await deleteBook(id);
      fetchBooks();
    } catch (error) {
      console.error('Error deleting book:', error);
    }
  };

  const resetForm = () => {
    setCurrentBook({
      title: '',
      genreId: '',
      publishYear: '',
      isbn: '',
      copiesAvailable: 0,
      authorIds: []
    });
    setOpenDialog(false);
    setIsEditing(false);
  };

  const getGenreNameById = (genreId) => {
    const genre = genres.find(g => g.id === genreId);
    return genre ? genre.name : '-';
  };

  const getAuthorNamesByIds = (authorIds) => {
    return authorIds.map(id => {
      const author = authors.find(a => a.id === id);
      return author ? author.name : '';
    }).filter(name => name !== '');
  };

  return (
    <Box>
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={3}>
        <Typography variant="h4">Books</Typography>
        <Button
          variant="contained"
          startIcon={<Add />}
          onClick={() => {
            setCurrentBook({
              title: '',
              genreId: '',
              publishYear: '',
              isbn: '',
              copiesAvailable: 0,
              authorIds: []
            });
            setIsEditing(false);
            setOpenDialog(true);
          }}
        >
          Add Book
        </Button>
      </Box>

      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Title</TableCell>
              <TableCell>Genre</TableCell>
              <TableCell>Year</TableCell>
              <TableCell>ISBN</TableCell>
              <TableCell>Copies</TableCell>
              <TableCell>Authors</TableCell>
              <TableCell>Rating</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {books.map((book) => (
              <TableRow key={book.id}>
                <TableCell>{book.id}</TableCell>
                <TableCell>{book.title}</TableCell>
                <TableCell>{book.genre?.name || '-'}</TableCell>
                <TableCell>{book.publishYear || '-'}</TableCell>
                <TableCell>{book.isbn || '-'}</TableCell>
                <TableCell>{book.copiesAvailable}</TableCell>
                <TableCell>
                  {book.authors?.map(author => (
                    <Chip
                      key={author.id}
                      label={author.name}
                      size="small"
                      sx={{ mr: 0.5, mb: 0.5 }}
                    />
                  ))}
                </TableCell>
                <TableCell>{book.averageRating || '-'}</TableCell>
                <TableCell>
                  <IconButton onClick={() => {
                    setCurrentBook({
                      id: book.id,
                      title: book.title,
                      genreId: book.genre?.id || '',
                      publishYear: book.publishYear || '',
                      isbn: book.isbn || '',
                      copiesAvailable: book.copiesAvailable,
                      authorIds: book.authors?.map(a => a.id) || []
                    });
                    setIsEditing(true);
                    setOpenDialog(true);
                  }}>
                    <Edit color="primary" />
                  </IconButton>
                  <IconButton onClick={() => handleDeleteBook(book.id)}>
                    <Delete color="error" />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      {/* Add/Edit Dialog */}
      <Dialog open={openDialog} onClose={resetForm} fullWidth maxWidth="sm">
        <DialogTitle>{isEditing ? 'Edit Book' : 'Add New Book'}</DialogTitle>
        <DialogContent sx={{ pt: 2, '& > *': { my: 1 } }}>
          <TextField
            autoFocus
            fullWidth
            label="Title *"
            value={currentBook.title}
            onChange={(e) => setCurrentBook({
              ...currentBook,
              title: e.target.value
            })}
          />
          <FormControl fullWidth>
            <InputLabel>Genre *</InputLabel>
            <Select
              value={currentBook.genreId}
              label="Genre *"
              onChange={(e) => setCurrentBook({
                ...currentBook,
                genreId: e.target.value
              })}
            >
              {genres.map((genre) => (
                <MenuItem key={genre.id} value={genre.id}>
                  {genre.name}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
          <Autocomplete
            multiple
            options={authors}
            getOptionLabel={(option) => option.name}
            value={authors.filter(author => currentBook.authorIds.includes(author.id))}
            onChange={(e, newValue) => {
              setCurrentBook({
                ...currentBook,
                authorIds: newValue.map(author => author.id)
              });
            }}
            renderInput={(params) => (
              <TextField
                {...params}
                label="Authors *"
                placeholder="Select authors"
              />
            )}
            renderTags={(value, getTagProps) =>
              value.map((option, index) => (
                <Chip
                  label={option.name}
                  size="small"
                  {...getTagProps({ index })}
                />
              ))
            }
          />
          <TextField
            fullWidth
            label="Publish Year"
            type="number"
            value={currentBook.publishYear}
            onChange={(e) => setCurrentBook({
              ...currentBook,
              publishYear: e.target.value
            })}
          />
          <TextField
            fullWidth
            label="ISBN"
            value={currentBook.isbn}
            onChange={(e) => setCurrentBook({
              ...currentBook,
              isbn: e.target.value
            })}
          />
          <TextField
            fullWidth
            label="Copies Available"
            type="number"
            value={currentBook.copiesAvailable}
            onChange={(e) => setCurrentBook({
              ...currentBook,
              copiesAvailable: e.target.value
            })}
            inputProps={{ min: 0 }}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={resetForm}>Cancel</Button>
          <Button
            onClick={isEditing ? handleUpdateBook : handleAddBook}
            color="primary"
            variant="contained"
            disabled={
              !currentBook.title ||
              !currentBook.genreId ||
              currentBook.authorIds.length === 0
            }
          >
            {isEditing ? 'Update' : 'Save'}
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default BooksPage;