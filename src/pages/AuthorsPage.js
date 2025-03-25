import React, { useState, useEffect } from 'react';
import {
  Box, Typography, Button, TextField,
  Table, TableBody, TableCell, TableContainer,
  TableHead, TableRow, Paper, IconButton,
  Dialog, DialogTitle, DialogContent, DialogActions
} from '@mui/material';
import { Add, Edit, Delete } from '@mui/icons-material';
import { DatePicker } from '@mui/x-date-pickers';
import { getAuthors, createAuthor, updateAuthor, deleteAuthor } from '../services/api';
import dayjs from 'dayjs';

const AuthorsPage = () => {
  const [authors, setAuthors] = useState([]);
  const [newAuthor, setNewAuthor] = useState({
    name: '',
    birthDate: null
  });
  const [editingAuthor, setEditingAuthor] = useState(null);
  const [openDialog, setOpenDialog] = useState(false);

  useEffect(() => {
    fetchAuthors();
  }, []);

  const fetchAuthors = async () => {
    try {
      const response = await getAuthors();
      setAuthors(response.data);
    } catch (error) {
      console.error('Error fetching authors:', error);
    }
  };

  const handleAddAuthor = async () => {
    if (!newAuthor.name.trim()) return;

    try {
      const authorData = {
        name: newAuthor.name,
        birthDate: newAuthor.birthDate ? newAuthor.birthDate.format('YYYY-MM-DD') : null
      };
      await createAuthor(authorData);
      setNewAuthor({ name: '', birthDate: null });
      fetchAuthors();
    } catch (error) {
      console.error('Error adding author:', error);
    }
  };

  const handleUpdateAuthor = async () => {
    if (!editingAuthor?.name.trim()) return;

    try {
      const authorData = {
        name: editingAuthor.name,
        birthDate: editingAuthor.birthDate ? editingAuthor.birthDate.format('YYYY-MM-DD') : null
      };
      await updateAuthor(editingAuthor.id, authorData);
      setEditingAuthor(null);
      setOpenDialog(false);
      fetchAuthors();
    } catch (error) {
      console.error('Error updating author:', error);
    }
  };

  const handleDeleteAuthor = async (id) => {
    try {
      await deleteAuthor(id);
      fetchAuthors();
    } catch (error) {
      console.error('Error deleting author:', error);
    }
  };

  const formatDate = (dateString) => {
    if (!dateString) return '-';
    return dayjs(dateString).format('DD.MM.YYYY');
  };

  return (
    <Box>
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={3}>
        <Typography variant="h4">Authors</Typography>
        <Box display="flex" alignItems="center" gap={2}>
          <TextField
            size="small"
            label="Author Name"
            value={newAuthor.name}
            onChange={(e) => setNewAuthor({...newAuthor, name: e.target.value})}
          />
          <DatePicker
            label="Birth Date"
            value={newAuthor.birthDate}
            onChange={(date) => setNewAuthor({...newAuthor, birthDate: date})}
            slotProps={{ textField: { size: 'small', sx: { width: 150 } } }}
          />
          <Button
            variant="contained"
            startIcon={<Add />}
            onClick={handleAddAuthor}
          >
            Add
          </Button>
        </Box>
      </Box>

      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Name</TableCell>
              <TableCell>Birth Date</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {authors.map((author) => (
              <TableRow key={author.id}>
                <TableCell>{author.id}</TableCell>
                <TableCell>{author.name}</TableCell>
                <TableCell>{formatDate(author.birthDate)}</TableCell>
                <TableCell>
                  <IconButton onClick={() => {
                    setEditingAuthor({
                      id: author.id,
                      name: author.name,
                      birthDate: author.birthDate ? dayjs(author.birthDate) : null
                    });
                    setOpenDialog(true);
                  }}>
                    <Edit color="primary" />
                  </IconButton>
                  <IconButton onClick={() => handleDeleteAuthor(author.id)}>
                    <Delete color="error" />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      {/* Edit Dialog */}
      <Dialog open={openDialog} onClose={() => setOpenDialog(false)} fullWidth maxWidth="sm">
        <DialogTitle>Edit Author</DialogTitle>
        <DialogContent sx={{ pt: 2 }}>
          <TextField
            autoFocus
            margin="normal"
            label="Author Name"
            fullWidth
            value={editingAuthor?.name || ''}
            onChange={(e) => setEditingAuthor({
              ...editingAuthor,
              name: e.target.value
            })}
          />
          <DatePicker
            label="Birth Date"
            value={editingAuthor?.birthDate || null}
            onChange={(date) => setEditingAuthor({
              ...editingAuthor,
              birthDate: date
            })}
            slotProps={{ textField: { fullWidth: true, margin: 'normal' } }}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setOpenDialog(false)}>Cancel</Button>
          <Button onClick={handleUpdateAuthor} color="primary">Save</Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default AuthorsPage;