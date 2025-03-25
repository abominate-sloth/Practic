import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { CssBaseline, Container, ThemeProvider, createTheme } from '@mui/material';
import { LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import NavBar from './components/NavBar';
import HomePage from './pages/HomePage';
import GenresPage from './pages/GenresPage';
import AuthorsPage from './pages/AuthorsPage';
import RolesPage from './pages/RolesPage';
import UsersPage from './pages/UsersPage';
import BooksPage from './pages/BooksPage';
import ReviewsPage from './pages/ReviewsPage';
import IssuesPage from './pages/IssuesPage';


const theme = createTheme();


function App() {
  return (
    <ThemeProvider theme={theme}>
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <Router>
          <CssBaseline />
          <NavBar />
          <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
            <Routes>
              <Route path="/" element={<HomePage />} />
              <Route path="/genres" element={<GenresPage />} />
              <Route path="/authors" element={<AuthorsPage />} />
              <Route path="/roles" element={<RolesPage />} />
              <Route path="/users" element={<UsersPage />} />
              <Route path="/books" element={<BooksPage />} />
              <Route path="/reviews" element={<ReviewsPage />} />
              <Route path="/issues" element={<IssuesPage />} />
            </Routes>
          </Container>
        </Router>
      </LocalizationProvider>
    </ThemeProvider>
  );
}

export default App;