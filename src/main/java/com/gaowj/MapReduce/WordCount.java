package com.gaowj.MapReduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * created by gaowj.
 * created on 2021-09-06.
 * function:
 * origin ->
 */
public class WordCount {
    public static class WordMapper extends
            Mapper<LongWritable, Text, Text, IntWritable> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //得到一行数据,将其转换成String
            String line = value.toString();
            //将文件切割
            String[] words = line.split(",");
            //输出<单词，1>到下文
            for (String word : words)
                context.write(new Text(word), new IntWritable(1));
        }
    }

    public static class WordReducer extends
            Reducer<Text, IntWritable, Text, IntWritable> {
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int count = 0;
            //遍历一组kv中key，遍历迭代器中所有的值，进行累加
            for (IntWritable value : values)
                count += value.get();
            //输出单词的统计结果
            context.write(key, new IntWritable(count));
        }
    }

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        Job job = new Job(conf, "word count");

        //设置Job所需Jar包的本地路径
        job.setJarByClass(WordCount.class);

        //指定Job要使用的Mapper和Reduce业务类
        job.setMapperClass(WordMapper.class);
        job.setReducerClass(WordReducer.class);

        //指定Reduce完成后最终的数据kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

    }
}
