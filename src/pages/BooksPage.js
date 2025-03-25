import React, { useState, useEffect } from 'react';
import {
  Box, Typography, Button, TextField,
  Table, TableBody, TableCell, TableContainer,
  TableHead, TableRow, Paper, IconButton,
  Dialog, DialogTitle, DialogContent, DialogActions,
  MenuItem, Select, InputLabel, FormControl, Chip,
  Autocomplete
} from '@mui/material';
import { Add, Edit, Delete, Search } from '@mui/icons-material';
import {
  getBooks, createBook, updateBook, deleteBook,
  getGenres, getAuthors, filterBooks
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
  const [filters, setFilters] = useState({
    title: '',
    genreId: null,
    publishYear: null,
    isbn: '',
    copiesAvailable: null
  });
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    fetchInitialData();
  }, []);

  useEffect(() => {
    const timer = setTimeout(() => {
      fetchBooksWithFilters();
    }, 500);
    return () => clearTimeout(timer);
  }, [filters]);

  const fetchInitialData = async () => {
    try {
      setLoading(true);
      await Promise.all([fetchBooks(), fetchGenres(), fetchAuthors()]);
    } catch (error) {
      console.error('Error fetching initial data:', error);
    } finally {
      setLoading(false);
    }
  };

  const fetchBooks = async () => {
    try {
      const response = await getBooks();
      setBooks(response.data);
    } catch (error) {
      console.error('Error fetching books:', error);
    }
  };

  const fetchBooksWithFilters = async () => {
    try {
      setLoading(true);
      // Преобразуем фильтры, удаляя пустые значения
      const activeFilters = Object.fromEntries(
        Object.entries(filters).filter(([_, value]) =>
          value !== '' && value !== null && value !== undefined
        )
      );

      const response = await filterBooks(activeFilters);
      setBooks(response.data);
    } catch (error) {
      console.error('Error filtering books:', error);
    } finally {
      setLoading(false);
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
      fetchBooksWithFilters();
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
      fetchBooksWithFilters();
    } catch (error) {
      console.error('Error updating book:', error);
    }
  };

  const handleDeleteBook = async (id) => {
    try {
      await deleteBook(id);
      fetchBooksWithFilters();
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

  const resetFilters = () => {
    setFilters({
      title: '',
      genreId: null,
      publishYear: null,
      isbn: '',
      copiesAvailable: null
    });
  };

  const handleFilterChange = (name, value) => {
    setFilters(prev => ({
      ...prev,
      [name]: value === '' ? null : value
    }));
  };

  const getGenreNameById = (genreId) => {
    const genre = genres.find(g => g.id === genreId);
    return genre ? genre.name : '-';
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

      {/* Фильтры поиска */}
      <Box mb={3} display="flex" gap={2} alignItems="center" flexWrap="wrap">
        <TextField
          variant="outlined"
          size="small"
          placeholder="Search by title..."
          value={filters.title}
          onChange={(e) => handleFilterChange('title', e.target.value)}
          InputProps={{
            startAdornment: <Search color="action" sx={{ mr: 1 }} />
          }}
          sx={{ width: 300 }}
        />

        <FormControl size="small" sx={{ width: 300 }}>
          <InputLabel>Filter by genre</InputLabel>
          <Select
            value={filters.genreId || ''}
            label="Filter by genre"
            onChange={(e) => handleFilterChange('genreId', e.target.value)}
          >
            <MenuItem value="">All genres</MenuItem>
            {genres.map((genre) => (
              <MenuItem key={genre.id} value={genre.id}>
                {genre.name}
              </MenuItem>
            ))}
          </Select>
        </FormControl>

        <TextField
          variant="outlined"
          size="small"
          label="Publish Year"
          type="number"
          value={filters.publishYear || ''}
          onChange={(e) => handleFilterChange('publishYear', e.target.value)}
          sx={{ width: 150 }}
        />

        <TextField
          variant="outlined"
          size="small"
          label="ISBN"
          value={filters.isbn || ''}
          onChange={(e) => handleFilterChange('isbn', e.target.value)}
          sx={{ width: 200 }}
        />

        <TextField
          variant="outlined"
          size="small"
          label="Min Copies"
          type="number"
          value={filters.copiesAvailable || ''}
          onChange={(e) => handleFilterChange('copiesAvailable', e.target.value)}
          sx={{ width: 150 }}
          inputProps={{ min: 0 }}
        />

        <Button
          variant="outlined"
          onClick={resetFilters}
          disabled={Object.values(filters).every(
            val => val === '' || val === null || val === undefined
          )}
        >
          Reset filters
        </Button>
      </Box>

      {loading ? (
        <Box display="flex" justifyContent="center" p={3}>
          <Typography>Loading...</Typography>
        </Box>
      ) : (
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
      )}

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