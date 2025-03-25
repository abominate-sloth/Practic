import React, { useState, useEffect } from 'react';
import {
  Box, Typography, Button, TextField,
  Table, TableBody, TableCell, TableContainer,
  TableHead, TableRow, Paper, IconButton,
  Dialog, DialogTitle, DialogContent, DialogActions
} from '@mui/material';
import { Add, Edit, Delete } from '@mui/icons-material';
import { getGenres, createGenre, updateGenre, deleteGenre } from '../services/api';

const GenresPage = () => {
  const [genres, setGenres] = useState([]);
  const [newGenreName, setNewGenreName] = useState('');
  const [editingGenre, setEditingGenre] = useState(null);
  const [openDialog, setOpenDialog] = useState(false);

  useEffect(() => {
    fetchGenres();
  }, []);

  const fetchGenres = async () => {
    try {
      const response = await getGenres();
      setGenres(response.data);
    } catch (error) {
      console.error('Error fetching genres:', error);
    }
  };

  const handleAddGenre = async () => {
    if (!newGenreName.trim()) return;

    try {
      await createGenre(newGenreName);
      setNewGenreName('');
      fetchGenres();
    } catch (error) {
      console.error('Error adding genre:', error);
    }
  };

  const handleUpdateGenre = async () => {
    if (!editingGenre?.name.trim()) return;

    try {
      await updateGenre(editingGenre.id, editingGenre.name);
      setEditingGenre(null);
      setOpenDialog(false);
      fetchGenres();
    } catch (error) {
      console.error('Error updating genre:', error);
    }
  };

  const handleDeleteGenre = async (id) => {
    try {
      await deleteGenre(id);
      fetchGenres();
    } catch (error) {
      console.error('Error deleting genre:', error);
    }
  };

  return (
    <Box>
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={3}>
        <Typography variant="h4">Genres</Typography>
        <Box display="flex" alignItems="center" gap={2}>
          <TextField
            size="small"
            label="New Genre Name"
            value={newGenreName}
            onChange={(e) => setNewGenreName(e.target.value)}
          />
          <Button
            variant="contained"
            startIcon={<Add />}
            onClick={handleAddGenre}
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
              <TableCell>Genre Name</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {genres.map((genre) => (
              <TableRow key={genre.id}>
                <TableCell>{genre.id}</TableCell>
                <TableCell>{genre.name}</TableCell>
                <TableCell>
                  <IconButton onClick={() => {
                    setEditingGenre(genre);
                    setOpenDialog(true);
                  }}>
                    <Edit color="primary" />
                  </IconButton>
                  <IconButton onClick={() => handleDeleteGenre(genre.id)}>
                    <Delete color="error" />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      {/* Edit Dialog */}
      <Dialog open={openDialog} onClose={() => setOpenDialog(false)}>
        <DialogTitle>Edit Genre</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            label="Genre Name"
            fullWidth
            value={editingGenre?.name || ''}
            onChange={(e) => setEditingGenre({
              ...editingGenre,
              name: e.target.value
            })}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setOpenDialog(false)}>Cancel</Button>
          <Button onClick={handleUpdateGenre} color="primary">Save</Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default GenresPage;