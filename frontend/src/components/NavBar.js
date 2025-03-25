import { AppBar, Toolbar, Typography, Button } from '@mui/material';
import { Link } from 'react-router-dom';

const NavBar = () => {
  return (
    <AppBar position="static">
      <Toolbar>
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          Library System
        </Typography>
        <Button color="inherit" component={Link} to="/">Home</Button>
        <Button color="inherit" component={Link} to="/genres">Genres</Button>
        <Button color="inherit" component={Link} to="/authors">Authors</Button>
        <Button color="inherit" component={Link} to="/roles">Roles</Button>
        <Button color="inherit" component={Link} to="/users">Users</Button>
        <Button color="inherit" component={Link} to="/books">Books</Button>
        <Button color="inherit" component={Link} to="/reviews">Reviews</Button>
        <Button color="inherit" component={Link} to="/issues">Issues</Button>
           </Toolbar>
    </AppBar>
  );
};

export default NavBar;