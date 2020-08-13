using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;

namespace Server.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        public HttpClient Http;

        public UserController()
        {
            Http = new HttpClient { BaseAddress = new Uri("http://localhost:8080/") };
        }


        [HttpGet("/login")]
        public async Task<List<bool>> Login(
            [FromQuery] string username,
            [FromBody] string password)
        {
            HttpResponseMessage rm = await Http.GetAsync($"user/login?username={username}&password={password}");
            string json = await rm.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<List<bool>>(json);
        }

        [HttpPost("/create")]
        public async Task<List<bool>> Register(
            [FromQuery] string username,
            [FromBody] string password)
        {
            HttpResponseMessage rm = await Http.PostAsync("user/create", new StringContent(username + password));
            string json = await rm.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<List<bool>>(json);
        }
    }
}
