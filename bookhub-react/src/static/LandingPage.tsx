import {
  Box,
  Container,
  Typography,
  Button,
  Grid,
  Card,
  CardContent
} from '@mui/material';
import { Star } from '@mui/icons-material';

interface LandingPageProps {
  onNavigate?: (path: string) => void;
}

export const LandingPage: React.FC<LandingPageProps> = ({ 
  onNavigate = () => {} 
}) => {
  
  const features = [
    {
      icon: '📚',
      title: 'Track Your Library',
      description: 'Keep all your books organized in custom shelves: currently reading, want to read, and completed.',
    },
    {
      icon: '⭐',
      title: 'Rate & Review',
      description: 'Share your thoughts with our star rating system and write detailed reviews to help other readers.',
    },
    {
      icon: '📊',
      title: 'Reading Analytics',
      description: 'Visualize your reading habits with insightful charts and track your annual goals.',
    },
    {
      icon: '👥',
      title: 'Social Reading',
      description: 'Connect with fellow book lovers and participate in reading challenges with our community.',
    },
    {
      icon: '🔍',
      title: 'Smart Search',
      description: 'Find books instantly with advanced search featuring filters by genre, author, and ratings.',
    },
    {
      icon: '📱',
      title: 'Mobile Ready',
      description: 'Access your library anywhere with our responsive design that works on all devices.',
    },
  ];

  const testimonials = [
    {
      text: "BookHub has completely transformed how I track my reading. The analytics feature helps me stay motivated to reach my yearly goals!",
      author: "Sarah Chen",
      subtitle: "Avid Reader, 127 books tracked",
      rating: 5,
    },
    {
      text: "The social features are amazing! I've discovered so many great books through the community recommendations.",
      author: "Marcus Johnson",
      subtitle: "Book Club Leader, 89 books tracked",
      rating: 5,
    },
    {
      text: "Clean interface, powerful features. As a developer, I appreciate the attention to UX detail and performance.",
      author: "Alex Rivera",
      subtitle: "Software Engineer, 203 books tracked",
      rating: 5,
    },
  ];

  const stats = [
    { number: '10K+', label: 'Books Tracked' },
    { number: '2.5K+', label: 'Active Readers' },
    { number: '15K+', label: 'Reviews Written' },
  ];

  return (
    <Box>
      {/* Hero Section */}
      <Box
        sx={{
          background: 'linear-gradient(135deg, #2c3e50, #34495e)',
          color: 'white',
          textAlign: 'center',
          py: 8,
          position: 'relative',
        }}
      >
        <Container maxWidth="lg">
          <Typography variant="h1" gutterBottom>
            BookHub
          </Typography>
          <Typography 
            variant="h5" 
            sx={{ mb: 4, opacity: 0.9, fontWeight: 400 }}
          >
            Track, Review, and Share Your Reading Journey
          </Typography>
          
          {/* Stats */}
          <Grid container spacing={4} justifyContent="center" sx={{ mb: 4 }}>
            {stats.map((stat) => (
              <Grid key={stat.label}>
                <Box>
                  <Typography 
                    variant="h3" 
                    sx={{ fontWeight: 'bold', color: '#3498db' }}
                  >
                    {stat.number}
                  </Typography>
                  <Typography variant="body1" sx={{ opacity: 0.8 }}>
                    {stat.label}
                  </Typography>
                </Box>
              </Grid>
            ))}
          </Grid>

          {/* CTA Buttons */}
          <Box sx={{ display: 'flex', gap: 2, justifyContent: 'center', flexWrap: 'wrap' }}>
            <Button 
              variant="contained" 
              size="large"
              onClick={() => onNavigate('/register')}
              sx={{ 
                minWidth: 200,
                background: 'linear-gradient(135deg, #3498db, #2980b9)',
                '&:hover': {
                  background: 'linear-gradient(135deg, #2980b9, #1f5f8b)',
                },
              }}
            >
              Get Started Free
            </Button>
            <Button 
              variant="outlined" 
              size="large"
              onClick={() => onNavigate('/demo')}
              sx={{ 
                minWidth: 200,
                color: 'white',
                borderColor: 'white',
                '&:hover': {
                  backgroundColor: 'white',
                  color: '#2c3e50',
                },
              }}
            >
              View Demo
            </Button>
          </Box>
        </Container>
      </Box>

      {/* Features Section */}
      <Container maxWidth="lg" sx={{ py: 8 }}>
        <Typography 
          variant="h2" 
          textAlign="center" 
          sx={{ mb: 6, color: '#2c3e50' }}
        >
          Why Choose BookHub?
        </Typography>
        
        <Grid container spacing={4}>
          {features.map((feature) => (
            <Grid size={{ xs: 12, md: 6, lg: 4 }} key={feature.title}>
              <Card 
                sx={{ 
                  height: '100%', 
                  textAlign: 'center',
                  p: 2,
                  '&:hover': {
                    transform: 'translateY(-5px)',
                    boxShadow: '0 8px 25px rgba(0,0,0,0.15)',
                  },
                  transition: 'all 0.3s ease',
                }}
              >
                <CardContent>
                  <Typography variant="h3" sx={{ mb: 2, fontSize: '3rem' }}>
                    {feature.icon}
                  </Typography>
                  <Typography variant="h6" gutterBottom sx={{ fontWeight: 600 }}>
                    {feature.title}
                  </Typography>
                  <Typography variant="body2" color="text.secondary">
                    {feature.description}
                  </Typography>
                </CardContent>
              </Card>
            </Grid>
          ))}
        </Grid>
      </Container>

      {/* Testimonials Section */}
      <Box sx={{ backgroundColor: '#f8f9fa', py: 8 }}>
        <Container maxWidth="lg">
          <Typography 
            variant="h2" 
            textAlign="center" 
            sx={{ mb: 6, color: '#2c3e50' }}
          >
            What Our Readers Say
          </Typography>
          
          <Grid container spacing={4}>
            {testimonials.map((testimonial, index) => (
              <Grid size={{ xs: 12, md: 4 }} key={index}>
                <Card sx={{ height: '100%', textAlign: 'center', p: 2 }}>
                  <CardContent>
                    {/* Star Rating */}
                    <Box sx={{ mb: 2 }}>
                      {Array.from({ length: testimonial.rating }, (_, i) => (
                        <Star key={i} sx={{ color: '#f1c40f', fontSize: '1.5rem' }} />
                      ))}
                    </Box>
                    
                    <Typography 
                      variant="body1" 
                      sx={{ mb: 3, fontStyle: 'italic', lineHeight: 1.6 }}
                    >
                      "{testimonial.text}"
                    </Typography>
                    
                    <Box sx={{ pt: 2, borderTop: '1px solid #e9ecef' }}>
                      <Typography variant="subtitle1" fontWeight="bold" color="#2c3e50">
                        {testimonial.author}
                      </Typography>
                      <Typography variant="body2" color="text.secondary">
                        {testimonial.subtitle}
                      </Typography>
                    </Box>
                  </CardContent>
                </Card>
              </Grid>
            ))}
          </Grid>
        </Container>
      </Box>

      {/* Final CTA Section */}
      <Box
        sx={{
          background: 'linear-gradient(135deg, #3498db, #2980b9)',
          color: 'white',
          py: 8,
          textAlign: 'center',
        }}
      >
        <Container maxWidth="lg">
          <Typography variant="h2" gutterBottom>
            Ready to Start Your Reading Journey?
          </Typography>
          <Typography variant="h6" sx={{ mb: 4, opacity: 0.9 }}>
            Join thousands of readers who are already tracking their books with BookHub. It's free to get started!
          </Typography>
          
          <Button
            variant="contained"
            size="large"
            onClick={() => onNavigate('/register')}
            sx={{
              backgroundColor: 'white',
              color: '#3498db',
              fontWeight: 'bold',
              px: 4,
              py: 2,
              '&:hover': {
                backgroundColor: '#f8f9fa',
                transform: 'translateY(-2px)',
              },
            }}
          >
            Create Your Free Account
          </Button>
          
          <Typography variant="body2" sx={{ mt: 2, opacity: 0.8 }}>
            No credit card required • Free forever
          </Typography>
        </Container>
      </Box>
    </Box>
  );
};