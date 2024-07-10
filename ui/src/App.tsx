import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import React from 'react';
import WeatherTable from './WeatherTable';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
    },
  },
});


const App = () => (
  <QueryClientProvider client={queryClient}>
    <WeatherTable/>
  </QueryClientProvider>
);

export default App;
