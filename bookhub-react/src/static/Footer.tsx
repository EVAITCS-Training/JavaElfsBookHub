import {
  Box,
  Container,
  Typography,
  Grid,
  Link,
  IconButton,
  Divider,
} from '@mui/material';

interface FooterProps {
  onNavigate?: (path: string) => void;
}

export const Footer: React.FC<FooterProps> = ({ 
  onNavigate = () => {} 
}) => {
  const currentYear = new Date().getFullYear();

  const handleClick = (path: string) => {
    onNavigate(path);
  };

  const quickLinks = [
    { label: 'Home', path: '/' },
    { label: 'My Books', path: '/books' },
    { label: 'Search Books', path: '/search' },
    { label: 'Community', path: '/community' },
  ];

  const supportLinks = [
    { label: 'Help Center', path: '/help' },
    { label: 'Contact Us', path: '/contact' },
    { label: 'FAQ', path: '/faq' },
    { label: 'Send Feedback', path: '/feedback' },
  ];

  const socialIcons = ['📘', '🐦', '📷', '💼'];

  return (
    <Box
      component="footer"
      sx={{
        background: 'linear-gradient(135deg, #2c3e50, #34495e)',
        color: 'white',
        py: 6,
        mt: 'auto',
        position: 'relative',
        '&::before': {
          content: '""',
          position: 'absolute',
          top: 0,
          left: 0,
          right: 0,
          height: '1px',
          background: 'linear-gradient(90deg, transparent, #3498db, transparent)',
        },
      }}
    >
      <Container maxWidth="lg">
        <Grid container spacing={4} sx={{ mb: 4 }}>
          {/* Company Info */}
          <Grid size={{ xs: 12, md: 4 }}>
            <Typography variant="h6" sx={{ color: '#3498db', mb: 2 }}>
              BookHub
            </Typography>
            <Typography 
              variant="body2" 
              sx={{ color: '#bdc3c7', lineHeight: 1.8, mb: 2 }}
            >
              Your ultimate companion for tracking, reviewing, and sharing your reading journey. 
              Join thousands of readers discovering their next favorite book.
            </Typography>
            
            {/* Social Links */}
            <Box sx={{ display: 'flex', gap: 1 }}>
              {socialIcons.map((icon, index) => (
                <IconButton
                  key={index}
                  sx={{
                    width: 40,
                    height: 40,
                    backgroundColor: 'rgba(52, 152, 219, 0.2)',
                    color: '#3498db',
                    '&:hover': {
                      backgroundColor: '#3498db',
                      color: 'white',
                      transform: 'translateY(-3px)',
                    },
                  }}
                >
                  {icon}
                </IconButton>
              ))}
            </Box>
          </Grid>

          {/* Quick Links */}
          <Grid size={{ xs: 12, sm: 6, md: 4 }}>
            <Typography variant="h6" sx={{ color: '#3498db', mb: 2 }}>
              Quick Links
            </Typography>
            {quickLinks.map((link) => (
              <Link
                key={link.label}
                component="button"
                onClick={() => handleClick(link.path)}
                sx={{
                  display: 'block',
                  color: '#bdc3c7',
                  textDecoration: 'none',
                  mb: 0.5,
                  textAlign: 'left',
                  border: 'none',
                  background: 'none',
                  cursor: 'pointer',
                  transition: 'all 0.3s ease',
                  '&:hover': {
                    color: '#3498db',
                    transform: 'translateX(5px)',
                  },
                }}
              >
                {link.label}
              </Link>
            ))}
          </Grid>

          {/* Support */}
          <Grid size={{ xs: 12, sm: 6, md: 4 }}>
            <Typography variant="h6" sx={{ color: '#3498db', mb: 2 }}>
              Support
            </Typography>
            {supportLinks.map((link) => (
              <Link
                key={link.label}
                component="button"
                onClick={() => handleClick(link.path)}
                sx={{
                  display: 'block',
                  color: '#bdc3c7',
                  textDecoration: 'none',
                  mb: 0.5,
                  textAlign: 'left',
                  border: 'none',
                  background: 'none',
                  cursor: 'pointer',
                  transition: 'all 0.3s ease',
                  '&:hover': {
                    color: '#3498db',
                    transform: 'translateX(5px)',
                  },
                }}
              >
                {link.label}
              </Link>
            ))}
          </Grid>
        </Grid>

        {/* Footer Bottom */}
        <Divider sx={{ borderColor: 'rgba(189, 195, 199, 0.2)', mb: 2 }} />
        <Box sx={{ textAlign: 'center' }}>
          <Typography variant="body2" sx={{ color: '#95a5a6' }}>
            © {currentYear}{' '}
            <Link 
              href="https://evaitconsulting.com" 
              target="_blank"
              sx={{ color: '#3498db', textDecoration: 'none' }}
            >
              EVA IT Consulting Services
            </Link>
            . All rights reserved. | Made with ❤️ for book lovers
          </Typography>
        </Box>
      </Container>
    </Box>
  );
};
