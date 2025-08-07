// bookhub-react/src/static/Navbar.tsx
import React from 'react';
import {
    AppBar,
    Box,
    Button,
    Container,
    Toolbar,
    Typography
} from '@mui/material'
import {
  Home,
  Book,
  Search,
  People,
  Person,
  Login,
} from '@mui/icons-material';

interface NavbarProps {
    user?: any;
    onNavigate?: (path: string) => void;
    onLogout?: () => void;
}

const Navbar: React.FC<NavbarProps> = ({
    user,
    onNavigate = () => {},
    onLogout = () => {}
}) => {
    const handleClick = (path: string): void => {
        onNavigate(path)
    }
    
    return (
        <AppBar
            position='sticky'
            sx={{
                background: 'linear-gradient(135deg, #2c3e50, #34495e)',
                boxShadow: '0 2px 15px rgba(44, 62, 80, 0.2)'
            }}
        >
            <Container maxWidth="lg">
                <Toolbar>
                    <Typography 
                        variant='h6'
                        sx={{
                            flexGrow: 1,
                            display: 'flex',
                            alignItems: 'center',
                            gap: 1,
                            fontWeight: 700,
                            cursor: 'pointer',
                            '&:hover': { color: '#3498db'}
                        }}
                    >
                        📚 BookHub
                    </Typography>
                    <Box sx={{ display: { xs: 'none', md: 'flex' }, gap: 1 }}>
                        <Button 
                        color="inherit" 
                        startIcon={<Home />}
                        onClick={() => handleClick('/')}
                        >
                        Home
                        </Button>
                        
                        <Button 
                        color="inherit" 
                        startIcon={<Book />}
                        onClick={() => handleClick('/books')}
                        >
                        My Books
                        </Button>
                        
                        <Button 
                        color="inherit" 
                        startIcon={<Search />}
                        onClick={() => handleClick('/search')}
                        >
                        Search
                        </Button>
                        
                        <Button 
                        color="inherit" 
                        startIcon={<People />}
                        onClick={() => handleClick('/community')}
                        >
                        Community
                        </Button>

                        {/* Auth Buttons */}
                        {user ? (
                        <>
                            <Button 
                            color="inherit" 
                            startIcon={<Person />}
                            onClick={() => handleClick('/profile')}
                            >
                            Profile
                            </Button>
                            <Button 
                            color="inherit"
                            onClick={onLogout}
                            >
                            Logout
                            </Button>
                        </>
                        ) : (
                        <>
                            <Button 
                            color="inherit" 
                            startIcon={<Login />}
                            onClick={() => handleClick('/login')}
                            >
                            Login
                            </Button>
                            <Button 
                            variant="outlined"
                            sx={{ 
                                color: 'white', 
                                borderColor: 'white',
                                '&:hover': { backgroundColor: 'white', color: '#2c3e50' }
                            }}
                            onClick={() => handleClick('/register')}
                            >
                            Sign Up
                            </Button>
                        </>
                        )}
                    </Box>
                </Toolbar>
            </Container>

        </AppBar>
    );
}

export default Navbar;